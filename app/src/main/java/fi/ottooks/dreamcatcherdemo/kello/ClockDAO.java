package fi.ottooks.dreamcatcherdemo.kello;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fi.ottooks.dreamcatcherdemo.Clock;

/**
 * The ClockDAO class for Dream catcher
 *     Used to create the ROOM database operations
 *     DAO = Data Access Objects
 */
@Dao
public interface ClockDAO {

    @Insert
    void insert(Clock clock);

    @Query("DELETE FROM clock_table")
    void deleteAll();

    @Query("SELECT * FROM clock_table ORDER BY hour ASC, min ASC")
    LiveData<List<Clock>> getClocks();

    @Update
    void update(Clock clock);

}
