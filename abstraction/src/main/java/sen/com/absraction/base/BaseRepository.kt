package sen.com.absraction.base

/**
 * Created by korneliussendy on 29/02/20,
 * at 00.45.
 * My Application
 */
abstract class BaseRepository<DataStore> {
    protected var localDataStore: DataStore? = null
    protected var remoteDataStore: DataStore? = null

    fun init(localDataStore: DataStore, remoteDataStore: DataStore) {
        this.localDataStore = localDataStore
        this.remoteDataStore = remoteDataStore
    }
}