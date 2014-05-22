package android.oldfeel.yanzhuang.fragment.list;

import android.content.Intent;
import android.oldfeel.yanzhuang.InformationDetail;
import android.oldfeel.yanzhuang.adapter.InformationListAdapter;
import android.oldfeel.yanzhuang.base.BaseListFragment;
import android.oldfeel.yanzhuang.item.InformationItem;
import android.oldfeel.yanzhuang.util.NetUtil;

/**
 * 活动/商家服务/个人服务列表
 * 
 * @author oldfeel
 * 
 */
public class InformationListFragment extends BaseListFragment {
	public static InformationListFragment newInstance(NetUtil netUtil) {
		InformationListFragment fragment = new InformationListFragment();
		fragment.netUtil = netUtil;
		return fragment;
	}

	@Override
	public void onItemClick(int position) {
		InformationItem item = (InformationItem) adapter.getItem(position);
		Intent intent = new Intent(getActivity(), InformationDetail.class);
		intent.putExtra("item", item);
		startActivity(intent);
	}

	@Override
	public void initAdapter() {
		adapter = new InformationListAdapter(getActivity());
	}

}