package com.wizard.hastar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wizard.hastar.MyApplication;
import com.wizard.hastar.R;
import com.wizard.hastar.ui.money_manager.util.RecordManager;
import com.wizard.hastar.util.HaStarUtil;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 伟平 on 2015/10/20.
 */

public class DrawerMonthViewRecyclerViewAdapter
        extends RecyclerView.Adapter<DrawerMonthViewRecyclerViewAdapter.viewHolder> {

    private ArrayList<Double> expenses;
    private ArrayList<Integer> records;
    private ArrayList<Integer> months;
    private ArrayList<Integer> years;

    private Context mContext;

    OnItemClickListener onItemClickListener;

    public DrawerMonthViewRecyclerViewAdapter(Context context) {
        mContext = context;
        expenses = new ArrayList<>();
        records = new ArrayList<>();
        months = new ArrayList<>();
        years = new ArrayList<>();

        if (RecordManager.getInstance(MyApplication.getAppContext()).RECORDS.size() != 0) {

            int currentYear = RecordManager.RECORDS.
                    get(RecordManager.RECORDS.size() - 1).getCalendar().get(Calendar.YEAR);
            int currentMonth = RecordManager.RECORDS.
                    get(RecordManager.RECORDS.size() - 1).getCalendar().get(Calendar.MONTH);
            double currentMonthSum = 0;
            int currentMonthRecord = 0;

            for (int i = RecordManager.RECORDS.size() - 1; i >= 0; i--) {
                if (RecordManager.RECORDS.get(i).getCalendar().get(Calendar.YEAR) == currentYear
                        && RecordManager.RECORDS.get(i).
                        getCalendar().get(Calendar.MONTH) == currentMonth) {
                    currentMonthSum += RecordManager.RECORDS.get(i).getMoney();
                    currentMonthRecord++;
                } else {
                    expenses.add(currentMonthSum);
                    records.add(currentMonthRecord);
                    years.add(currentYear);
                    months.add(currentMonth);
                    currentMonthSum = RecordManager.RECORDS.get(i).getMoney();
                    currentMonthRecord = 1;
                    currentYear = RecordManager.RECORDS.get(i).getCalendar().get(Calendar.YEAR);
                    currentMonth = RecordManager.RECORDS.get(i).getCalendar().get(Calendar.MONTH);
                }
            }
            expenses.add(currentMonthSum);
            records.add(currentMonthRecord);
            years.add(currentYear);
            months.add(currentMonth);

        }


    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    @Override
    public DrawerMonthViewRecyclerViewAdapter.viewHolder
        onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_month_view_drawer, parent, false);
        return new viewHolder(view) {
        };

    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
        holder.month.setText(HaStarUtil.GetMonthShort(months.get(position) + 1));
        holder.month.setTypeface(HaStarUtil.getInstance().typefaceLatoLight);

        holder.year.setText(years.get(position) + "");
        holder.year.setTypeface(HaStarUtil.getInstance().typefaceLatoLight);

        holder.sum.setText(HaStarUtil.getInstance().GetInRecords(records.get(position)));
        holder.sum.setTypeface(HaStarUtil.getInstance().typefaceLatoLight);

        holder.money.setText(HaStarUtil.getInstance().GetInMoney((int) (double) (expenses.get(position))));
        holder.money.setTypeface(HaStarUtil.getInstance().typefaceLatoLight);
    }

    public class viewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        @BindView(R.id.month)
        TextView month;
        @BindView(R.id.year)
        TextView year;
        @BindView(R.id.money)
        TextView money;
        @BindView(R.id.sum)
        TextView sum;

        viewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }

}
