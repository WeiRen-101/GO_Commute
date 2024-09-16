package tw.com.hkt.myapplication.Member

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Base64
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.R
import tw.com.hkt.myapplication.databinding.ActivityRegisterBinding
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.regex.Pattern

class register : AppCompatActivity() {

    var loginF: Boolean = false // LoginFlag確認是否已登入

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this, "SystemAccount")

        binding.imageButton.setOnClickListener {    // 退出
            finish()
        }

        binding.button38.setOnClickListener {   // 登入
            finish()
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        binding.button16.setOnClickListener {   // 註冊
            val email = binding.editTextText.text.toString()
            val password = binding.editTextTextPassword2.text.toString()
            val confirmPassword = binding.editTextTextPassword.text.toString()
            val selectedQuestionIndex = (binding.spinner.selectedItemPosition + 1).toString() // 下拉式选单的选项索引
            val securityAnswer = binding.editTextSecurityAnswer.text.toString()

            // 檢查是否有任何字段為空
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || securityAnswer.isEmpty()) {
                Toast.makeText(this, "請輸入完整資訊", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password == confirmPassword) {
                if (isValidEmail(email)) {
                    if (register(email, password, selectedQuestionIndex, securityAnswer)) {
                        Toast.makeText(this, "註冊成功", Toast.LENGTH_SHORT).show()
                        loginF = true               // 已登入
                        finish()
                        val intent = Intent(this, member::class.java)
                        startActivity(intent)       // 跳回會員介面
                    } else {
                        Toast.makeText(this, "註冊失敗", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "無效的電子郵件地址", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "密碼不一致", Toast.LENGTH_SHORT).show()
            }
        }

        // Spinner 下拉式選單
        val spinner: Spinner = findViewById(R.id.spinner)
        val items = arrayOf( // 安全提示問題
            "1.你購買的第一台車型號是什麼?",
            "2.你父母結婚的年份是什麼時候？",
            "3.你學會游泳的年齡是多少？",
            "4.你母親的姓氏？",
            "5.你的寵物叫什麼名字?"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            //正規表達式
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
        )
        //^[A-Z0-9._%+-]+：匹配一個或多個字母、數字或允許的特殊字符。
        //@[A-Z0-9.-]+：匹配 @ 後跟隨的一個或多個字母、數字或點。
        // \\.[A-Z]{2,6}$：匹配一個點，後面跟隨 2 到 6 個字母。
        return emailPattern.matcher(email).matches()
    }

    private fun register(email: String, password: String, questionIndex: String, answer: String): Boolean {
        val db = dbHelper.writableDatabase

        val salt = generateSalt()
        val hashedPassword = hashPassword(password, salt)

        val answerSalt = generateSalt()
        val hashedAnswer = hashPassword(answer, answerSalt)

        val values = ContentValues().apply {
            put("Email", email)
            put("PasswordSalt", salt)
            put("PasswordHash", hashedPassword)
            put("SecurityQuestionIndex", questionIndex.toInt())
            put("SecurityAnswerHash", hashedAnswer)
            put("SecurityAnswerSalt", answerSalt)
        }

        val result = db.insert("SystemAccount", null, values)
        return result != -1L
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

    private fun generateSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return Base64.encodeToString(salt, Base64.DEFAULT).trim()
    }

    private fun concatenateByteArrays(a: ByteArray, b: ByteArray): ByteArray {
        return ByteArray(a.size + b.size).apply {
            System.arraycopy(a, 0, this, 0, a.size)
            System.arraycopy(b, 0, this, a.size, b.size)
        }
    }

    private class DatabaseHelper(context: Context, val tableName: String) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS $tableName (" +
                    "BusinessEntityID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Email TEXT UNIQUE," +
                    "PasswordSalt TEXT," +
                    "PasswordHash TEXT," +
                    "SecurityQuestionIndex INTEGER," +
                    "SecurityAnswerHash TEXT," +
                    "SecurityAnswerSalt TEXT)")
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
