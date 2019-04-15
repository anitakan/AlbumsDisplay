package example.albums.com.display

import android.content.Context
import android.util.Log
import java.io.*


class FileUtil {
    companion object {
        fun readFromFile(context: Context): String {

            var jsonString = ""

            try {
                val inputStream = context.openFileInput("config.txt")

                if (inputStream != null) {
                    val inputStreamReader = InputStreamReader(inputStream)
                    val bufferedReader = BufferedReader(inputStreamReader)
                    var receiveString = bufferedReader.readLine()
                    val stringBuilder = StringBuilder()

                    while (receiveString != null) {
                        stringBuilder.append(receiveString)
                        receiveString = bufferedReader.readLine()
                    }

                    inputStream.close()
                    jsonString = stringBuilder.toString()
                }
            } catch (e: FileNotFoundException) {
                Log.e("login activity", "File not found: " + e.toString())
            } catch (e: IOException) {
                Log.e("login activity", "Can not read file: " + e.toString())
            }

            return jsonString
        }

        fun writeToFile(data: String, context: Context) {
            try {
                val outputStreamWriter = OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE))
                outputStreamWriter.write(data)
                outputStreamWriter.close()
            } catch (e: IOException) {
                Log.e("Exception", "File write failed: $e")
            }

        }
    }
}