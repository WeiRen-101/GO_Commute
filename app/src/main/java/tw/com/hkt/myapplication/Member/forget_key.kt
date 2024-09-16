package tw.com.hkt.myapplication.Member

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.databinding.ActivityForgetKeyBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.util.Base64
import android.util.Log
import java.nio.charset.StandardCharsets
import java.util.regex.Pattern

class forget_key : AppCompatActivity() {
    private lateinit var binding: ActivityForgetKeyBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetKeyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this, "SystemAccount")

        // Spinner
        val spinner = binding.spinner2
        val items = arrayOf(
            "1.你購買的第一台車型號是什麼?",
            "2.你父母結婚的年份是什麼時候？",
            "3.你學會游泳的年齡是多少？",
            "4.你母親的姓氏？",
            "5.你的寵物叫什麼名字?"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        binding.button16.setOnClickListener {
            val selectedQuestionIndex = binding.spinner2.selectedItemPosition + 1
            val userAnswer = binding.editTextAnswer.text.toString()
            val email = binding.editTextforgetpasswordEmail.text.toString()

            // Check if any input field is empty
            if (email.isEmpty() || userAnswer.isEmpty()) {
                Toast.makeText(this, "請輸入完整資訊", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if email format is valid
            if (!isValidEmail(email)) {
                Toast.makeText(this, "無效的電子郵件地址", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if email exists and authenticate security answer
            val (emailExists, isAnswerCorrect, isQuestionMatching) = authenticate(email, selectedQuestionIndex, userAnswer)
            when {
                !emailExists -> Toast.makeText(this, "帳號不存在", Toast.LENGTH_SHORT).show()
                !isQuestionMatching -> Toast.makeText(this, "安全問題不匹配", Toast.LENGTH_SHORT).show()
                isAnswerCorrect -> {
                    Toast.makeText(this, "答案正確", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, change_key::class.java)
                    startActivity(intent)
                    //傳送電子郵件給change_key頁面
                    //intent.putExtra("email", email)
                    //startActivity(intent)
                }
                else -> Toast.makeText(this, "答案錯誤", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageButton.setOnClickListener {
            finish()
        }
    }

    private fun authenticate(email: String, selectedQuestionIndex: Int, userAnswer: String): Triple<Boolean, Boolean, Boolean> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor? = db.rawQuery(
            "SELECT SecurityQuestionIndex, SecurityAnswerHash, SecurityAnswerSalt FROM SystemAccount WHERE Email=?",
            arrayOf(email)
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val questionIndex = it.getColumnIndex("SecurityQuestionIndex")
                val answerHashIndex = it.getColumnIndex("SecurityAnswerHash")
                val answerSaltIndex = it.getColumnIndex("SecurityAnswerSalt")

                if (questionIndex != -1 && answerHashIndex != -1 && answerSaltIndex != -1) {
                    val storedQuestionIndex = it.getInt(questionIndex)
                    val storedAnswerHash = it.getString(answerHashIndex)
                    val storedAnswerSalt = it.getString(answerSaltIndex)

                    val isQuestionMatching = selectedQuestionIndex == storedQuestionIndex
                    val isAnswerCorrect = if (isQuestionMatching) {
                        val userAnswerHash = hashPassword(userAnswer, storedAnswerSalt)
                        storedAnswerHash == userAnswerHash
                    } else {
                        false
                    }

                    return Triple(true, isAnswerCorrect, isQuestionMatching)
                } else {
                    Log.e("DatabaseError", "Column not found: SecurityQuestionIndex, SecurityAnswerHash, or SecurityAnswerSalt")
                    Toast.makeText(this, "數據庫錯誤", Toast.LENGTH_SHORT).show()
                    return Triple(false, false, false)
                }
            } else {
                Log.e("DatabaseError", "Cursor is empty") //帳號不存在
                return Triple(false, false, false)
            }
        } ?: Log.e("DatabaseError", "Cursor is null")
        return Triple(false, false, false)
    }

    private fun hashPassword(password: String, salt: String): String? {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            val source = password.toByteArray(StandardCharsets.UTF_8)
            val saltBytes = Base64.decode(salt, Base64.DEFAULT)
            val passAndSalt = concatenateByteArrays(source, saltBytes)
            val hash = digest.digest(passAndSalt)
            Base64.encodeToString(hash, Base64.DEFAULT).trim()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            null
        }
    }

    private fun concatenateByteArrays(a: ByteArray, b: ByteArray): ByteArray {
        return ByteArray(a.size + b.size).apply {
            System.arraycopy(a, 0, this, 0, a.size)
            System.arraycopy(b, 0, this, a.size, b.size)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
        )
        return emailPattern.matcher(email).matches()
    }

    private class DatabaseHelper(context: Context, private val tableName: String) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS $tableName (" +
                    "BusinessEntityID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Email TEXT UNIQUE," +
                    "PasswordSalt TEXT," +
                    "PasswordHash TEXT," +
                    "SecurityQuestionIndex INTEGER," +
                    "SecurityAnswerSalt TEXT," +
                    "SecurityAnswerHash TEXT)")
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $tableName")
            onCreate(db)
        }

        companion object {
            private const val DATABASE_NAME = "memberDB"
            private const val DATABASE_VERSION = 3
        }
    }
}
