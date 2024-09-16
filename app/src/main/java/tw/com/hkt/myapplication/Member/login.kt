package tw.com.hkt.myapplication.Member

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tw.com.hkt.myapplication.MainActivity
import tw.com.hkt.myapplication.databinding.ActivityLoginBinding
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)
        val editor = sharedPref.edit()

        dbHelper = DatabaseHelper(this, "SystemAccount")

        binding.button28.setOnClickListener {   //註冊
            finish()
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }
        binding.imageButton.setOnClickListener{ //退出
            finish()
        }

        binding.button19.setOnClickListener {
            val intent = Intent(this, forget_key::class.java)
            startActivity(intent)
        }
        binding.button18.setOnClickListener {   //登入
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextTextPassword2.text.toString()

            if (authenticate(email, password)) {
                Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show()
                editor.putBoolean("is_logged_in", true)
                editor.apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "登入失敗", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun authenticate(email: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT PasswordSalt, PasswordHash FROM SystemAccount WHERE Email=?", arrayOf(email))

        cursor?.use {
            if (it.moveToFirst()) {
                val passwordSaltIndex = it.getColumnIndex("PasswordSalt")
                val passwordHashIndex = it.getColumnIndex("PasswordHash")

                if (passwordSaltIndex != -1 && passwordHashIndex != -1) {
                    val passwordSalt = it.getString(passwordSaltIndex)
                    val truePasswordHash = it.getString(passwordHashIndex)

                    val passwordHash = hashPassword(password, passwordSalt)
                    return truePasswordHash == passwordHash
                } else {
                    Log.e("DatabaseError", "Column not found: PasswordSalt or PasswordHash")
                }
            } else {
                Log.e("DatabaseError", "Cursor is empty")
            }
        } ?: Log.e("DatabaseError", "Cursor is null")
        return false
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

    private class DatabaseHelper(context: AppCompatActivity, val tableName: String) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS $tableName (" +
                    "BusinessEntityID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Email TEXT UNIQUE," +
                    "PasswordSalt TEXT," +
                    "PasswordHash TEXT)")
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
