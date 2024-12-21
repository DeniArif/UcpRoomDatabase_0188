import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MatakuliahDao
import com.example.ucp2.entity.Dosen

@Database(entities = [Dosen::class], version = 1, exportSchema = false)
abstract class KrsDatabaseDosen : RoomDatabase() {
    abstract fun dosen(): DosenDao
    abstract fun matakuliah(): MatakuliahDao

    companion object {
        @Volatile
        private var Instance: KrsDatabaseDosen? = null

        fun getDatabase(context: Context): KrsDatabaseDosen {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabaseDosen::class.java,
                    "KrsDatabaseDosen"
                ).build().also { Instance = it }
            }
        }
    }
}
