package com.zkp.demos.modules.excel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkp.demos.R;

import java.util.List;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.modules.excel
 * @time: 2019/4/1 16:22
 * @description:
 */
public class ExcelRecyclerViewAdapter extends RecyclerView.Adapter<ExcelRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<ExcelInfoBean> mExcelInfoBeanList;

    public ExcelRecyclerViewAdapter(Context mContext, List<ExcelInfoBean> mExcelInfoBeanList) {
        this.mContext = mContext;
        this.mExcelInfoBeanList = mExcelInfoBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_excel_info, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvValue1.setText(getItem(i).getValue0() + "");
        viewHolder.tvValue2.setText(getItem(i).getValue1() + "");
        viewHolder.tvValue3.setText(getItem(i).getValue2() + "");
        viewHolder.tvValue4.setText(getItem(i).getValue3() + "");
        viewHolder.tvValue5.setText(getItem(i).getValue4() + "");
        viewHolder.tvValue6.setText(getItem(i).getValue5() + "");
        viewHolder.tvValue7.setText(getItem(i).getValue6() + "");
        viewHolder.tvValue8.setText(getItem(i).getValue7() + "");
        viewHolder.tvValue9.setText(getItem(i).getValue8() + "");
        viewHolder.tvValue10.setText(getItem(i).getValue9() + "");
        viewHolder.tvValue11.setText(getItem(i).getValue10() + "");
        viewHolder.tvValue12.setText(getItem(i).getValue11() + "");
        viewHolder.tvValue13.setText(getItem(i).getValue12() + "");
        viewHolder.tvValue14.setText(getItem(i).getValue13() + "");
        viewHolder.tvValue15.setText(getItem(i).getValue14() + "");
        viewHolder.tvValue16.setText(getItem(i).getValue15() + "");
        viewHolder.tvValue17.setText(getItem(i).getValue16() + "");
        viewHolder.tvValue18.setText(getItem(i).getValue17() + "");
        viewHolder.tvValue19.setText(getItem(i).getValue18() + "");
        viewHolder.tvValue20.setText(getItem(i).getValue19() + "");
    }

    @Override
    public int getItemCount() {
        return mExcelInfoBeanList.size();
    }

    public ExcelInfoBean getItem(int position) {
        return mExcelInfoBeanList.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvValue1, tvValue2, tvValue3, tvValue4, tvValue5, tvValue6, tvValue7,
                tvValue8, tvValue9, tvValue10, tvValue11, tvValue12, tvValue13, tvValue14,
                tvValue15, tvValue16, tvValue17, tvValue18, tvValue19, tvValue20;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvValue1 = itemView.findViewById(R.id.tvValue1);
            tvValue2 = itemView.findViewById(R.id.tvValue2);
            tvValue3 = itemView.findViewById(R.id.tvValue3);
            tvValue4 = itemView.findViewById(R.id.tvValue4);
            tvValue5 = itemView.findViewById(R.id.tvValue5);
            tvValue6 = itemView.findViewById(R.id.tvValue6);
            tvValue7 = itemView.findViewById(R.id.tvValue7);
            tvValue8 = itemView.findViewById(R.id.tvValue8);
            tvValue9 = itemView.findViewById(R.id.tvValue9);
            tvValue10 = itemView.findViewById(R.id.tvValue10);
            tvValue11 = itemView.findViewById(R.id.tvValue11);
            tvValue12 = itemView.findViewById(R.id.tvValue12);
            tvValue13 = itemView.findViewById(R.id.tvValue13);
            tvValue14 = itemView.findViewById(R.id.tvValue14);
            tvValue15 = itemView.findViewById(R.id.tvValue15);
            tvValue16 = itemView.findViewById(R.id.tvValue16);
            tvValue17 = itemView.findViewById(R.id.tvValue17);
            tvValue18 = itemView.findViewById(R.id.tvValue18);
            tvValue19 = itemView.findViewById(R.id.tvValue19);
            tvValue20 = itemView.findViewById(R.id.tvValue20);
        }
    }

}
