package me.xihuxiaolong.generalcomponent.module.uishow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.library.widget.TimePickerDialogplus;
import timber.log.Timber;

public class UICalendarFragment extends Fragment implements CalendarDatePickerDialogFragment.OnDateSetListener, TimePickerDialogplus.ConfirmListener {

    private static final String FRAG_TAG_DATE_PICKER_1 = "datePicker1";

    @BindView(R.id.selectedTime1)
    TextView selectedTime1;
    @BindView(R.id.selectedTime2)
    TextView selectedTime2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui_calendar, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.datePicker1)
    void datePicker1Click(View v) {
        DateTime now = DateTime.now();
        MonthAdapter.CalendarDay minDate = new MonthAdapter.CalendarDay(now.getYear(), now.getMonthOfYear() - 1, now.getDayOfMonth());

        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                .setDateRange(minDate, null);
        cdp.setOnDateSetListener(this);
        cdp.show(getChildFragmentManager(), FRAG_TAG_DATE_PICKER_1);
    }

    @OnClick(R.id.datePicker2)
    void datePicker2Click(View v) {
        TimePickerDialogplus timePickerDialogplus = new TimePickerDialogplus();
        timePickerDialogplus.getDialog(getContext(), DateTime.now(), DateTime.now().plusYears(33), this);
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        DateTime dateTime = new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0);
        selectedTime1.setText(getString(R.string.calendar_date_picker_result_values, year, monthOfYear + 1, dayOfMonth));
        selectedTime1.setTag(dateTime.getMillis());
    }

    @Override
    public void selectedTime(DateTime dateTime) {
//        Timber.d("dateTime", dateTime.toString("yyyy年MM月dd日 HH:mm:ss"));
        selectedTime2.setText(getString(R.string.calendar_date_picker_result_values, dateTime.year().get(), dateTime.monthOfYear().get(), dateTime.dayOfMonth().get()));

    }
}
