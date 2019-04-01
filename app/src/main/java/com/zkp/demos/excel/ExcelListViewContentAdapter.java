package com.zkp.demos.excel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zkp.demos.R;

import java.util.List;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.excel
 * @time: 2019/4/1 16:55
 * @description:
 */
public class ExcelListViewContentAdapter extends BaseAdapter {

    private Context mContext;
    private List<ExcelInfoBean> mExcelInfoBeanList;

    public ExcelListViewContentAdapter(Context mContext, List<ExcelInfoBean> mExcelInfoBeanList) {
        this.mContext = mContext;
        this.mExcelInfoBeanList = mExcelInfoBeanList;
    }

    @Override
    public int getCount() {
        return mExcelInfoBeanList.size();
    }

    @Override
    public ExcelInfoBean getItem(int position) {
        return mExcelInfoBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_excel_info, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvValue1 = convertView.findViewById(R.id.tvValue1);
            viewHolder.tvValue2 = convertView.findViewById(R.id.tvValue2);
            viewHolder.tvValue3 = convertView.findViewById(R.id.tvValue3);
            viewHolder.tvValue4 = convertView.findViewById(R.id.tvValue4);
            viewHolder.tvValue5 = convertView.findViewById(R.id.tvValue5);
            viewHolder.tvValue6 = convertView.findViewById(R.id.tvValue6);
            viewHolder.tvValue7 = convertView.findViewById(R.id.tvValue7);
            viewHolder.tvValue8 = convertView.findViewById(R.id.tvValue8);
            viewHolder.tvValue9 = convertView.findViewById(R.id.tvValue9);
            viewHolder.tvValue10 = convertView.findViewById(R.id.tvValue10);
            viewHolder.tvValue11 = convertView.findViewById(R.id.tvValue11);
            viewHolder.tvValue12 = convertView.findViewById(R.id.tvValue12);
            viewHolder.tvValue13 = convertView.findViewById(R.id.tvValue13);
            viewHolder.tvValue14 = convertView.findViewById(R.id.tvValue14);
            viewHolder.tvValue15 = convertView.findViewById(R.id.tvValue15);
            viewHolder.tvValue16 = convertView.findViewById(R.id.tvValue16);
            viewHolder.tvValue17 = convertView.findViewById(R.id.tvValue17);
            viewHolder.tvValue18 = convertView.findViewById(R.id.tvValue18);
            viewHolder.tvValue19 = convertView.findViewById(R.id.tvValue19);
            viewHolder.tvValue20 = convertView.findViewById(R.id.tvValue20);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvValue1.setText(getItem(position).getValue0() + "");
        viewHolder.tvValue2.setText(getItem(position).getValue1() + "");
        viewHolder.tvValue3.setText(getItem(position).getValue2() + "");
        viewHolder.tvValue4.setText(getItem(position).getValue3() + "");
        viewHolder.tvValue5.setText(getItem(position).getValue4() + "");
        viewHolder.tvValue6.setText(getItem(position).getValue5() + "");
        viewHolder.tvValue7.setText(getItem(position).getValue6() + "");
        viewHolder.tvValue8.setText(getItem(position).getValue7() + "");
        viewHolder.tvValue9.setText(getItem(position).getValue8() + "");
        viewHolder.tvValue10.setText(getItem(position).getValue9() + "");
        viewHolder.tvValue11.setText(getItem(position).getValue10() + "");
        viewHolder.tvValue12.setText(getItem(position).getValue11() + "");
        viewHolder.tvValue13.setText(getItem(position).getValue12() + "");
        viewHolder.tvValue14.setText(getItem(position).getValue13() + "");
        viewHolder.tvValue15.setText(getItem(position).getValue14() + "");
        viewHolder.tvValue16.setText(getItem(position).getValue15() + "");
        viewHolder.tvValue17.setText(getItem(position).getValue16() + "");
        viewHolder.tvValue18.setText(getItem(position).getValue17() + "");
        viewHolder.tvValue19.setText(getItem(position).getValue18() + "");
        viewHolder.tvValue20.setText(getItem(position).getValue19() + "");

        return convertView;
    }

    class ViewHolder {
        TextView tvValue1, tvValue2, tvValue3, tvValue4, tvValue5, tvValue6, tvValue7,
                tvValue8, tvValue9, tvValue10, tvValue11, tvValue12, tvValue13, tvValue14,
                tvValue15, tvValue16, tvValue17, tvValue18, tvValue19, tvValue20;
    }
}
