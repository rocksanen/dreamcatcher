package fi.ottooks.dreamcatcherdemo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClockDAO {

    @Insert
    void insert(Clock clock);

    @Query("DELETE FROM clock_table")
    void deleteAll();

    @Query("SELECT * FROM clock_table ORDER BY id ASC")
    LiveData<List<Clock>> getClocks();

    @Update
    void update(Clock clock);

}
