package tw.com.hkt.myapplication.Member

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.databinding.ActivityChangeKeyBinding
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom

class change_key : AppCompatActivity() {
    private lateinit var binding: ActivityChangeKeyBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeKeyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 接收傳送過來的電子郵件地址
        val intent = intent
        val email = intent.getStringExtra("email")

        dbHelper = DatabaseHelper(this, "SystemAccount")

        binding.button16.setOnClickListener {
            val newPassword = binding.editTextNewPassword.text.toString()
            val confirmNewPassword = binding.editTextsureNewPassword.text.toString()

            if (newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                Toast.makeText(this, "請輸入新密碼和確認新密碼", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPassword == confirmNewPassword) {
                if (updatePassword(email, newPassword)) {
                    Toast.makeText(this, "密碼更新成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "密碼更新失敗", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "兩次輸入的密碼不一致", Toast.LENGTH_SHORT).show()
            }
        }

        // 設置按鈕點擊事件來返回上個畫面
        binding.imageButton.setOnClickListener {
            finish()
        }
    }

    private fun updatePassword(email: String?, newPassword: String): Boolean {
        if (email == null) {
            return false
        }

        val db = dbHelper.writableDatabase

        val salt = generateSalt()
        val hashedPassword = hashPassword(newPassword, salt)

        val values = ContentValues().apply {
            put("PasswordSalt", salt)
            put("PasswordHash", hashedPassword)
        }

        val selection = "Email = ?"
        val selectionArgs = arrayOf(email)

        val count = db.update(
            "SystemAccount",
            values,
            selection,
            selectionArgs
        )

        return count > 0
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