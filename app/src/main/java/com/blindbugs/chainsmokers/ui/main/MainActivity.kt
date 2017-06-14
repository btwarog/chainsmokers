package com.blindbugs.chainsmokers.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.blindbugs.chainsmokers.R
import com.blindbugs.chainsmokers.domain.model.Day
import com.blindbugs.chainsmokers.domain.model.Entry
import com.blindbugs.chainsmokers.infrastructure.di.activity.MainActivityModule
import com.blindbugs.chainsmokers.infrastructure.extension.app
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.MainPresenterView {
  val component by lazy { app.component.plus(MainActivityModule(this)) }

  @Inject
  lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    component.inject(this)

    presenter.view = this
    presenter.init()
    createEntryButton.setOnClickListener { presenter.createEntry() }

  }

  override fun onEntriesByDayUpdated(days: Map<LocalDate, Day>) {
    val output = StringBuilder()
    days.forEach { output.appendln(it.key.format(DateTimeFormatter.ISO_LOCAL_DATE) + ": " + it.value) }
    longToast(output)
  }

  override fun onEntryCreated(entry: Entry) {
    toast("Entry created at " + entry.timestamp)
  }
}
