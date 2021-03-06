package com.blindbugs.chainsmokers.data.datasource

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.blindbugs.chainsmokers.data.datasource.model.CigaretteEntity
import com.blindbugs.chainsmokers.data.datasource.model.DailyCigarettesEntity

@Dao
interface CigaretteDao {

  @Insert(onConflict = REPLACE)
  fun insert(cigaretteEntity: CigaretteEntity)

  @Query("Select id, datetime from cigarettes")
  fun getAll(): List<CigaretteEntity>

  @Query("Select date(datetime) as day, count(*) as count from cigarettes group by date(datetime)")
  fun getDailyCigarettes() : List<DailyCigarettesEntity>
}