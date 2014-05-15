package android.oldfeel.yanzhuang.base;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.oldfeel.yanzhuang.app.Constant;
import android.oldfeel.yanzhuang.util.ViewUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.oldfeel.yanzhuang.R;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 适配器基类
 * 
 * @author oldfeel
 * 
 *         Created on: 2014-1-10
 */
public abstract class BaseBaseAdapter<T> extends BaseAdapter {
	protected DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected LayoutInflater inflater;
	protected Context context;
	protected ArrayList<T> list = new ArrayList<T>();
	private boolean isAddOver = false; // 是否加载完成
	private long pagesize = Constant.PAGE_SIZE;

	public BaseBaseAdapter(Context context) {
		this(context, -1);
	}

	public BaseBaseAdapter(Context context, int id) {
		this.context = context;
		id = (id == -1) ? R.drawable.ic_launcher : id;
		inflater = LayoutInflater.from(context);
		if (id > 0)
			options = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(id).showImageOnFail(id)
					.cacheInMemory(true).cacheOnDisc(true).build();
	}

	public void addResult(int page, String result) {
		if (page == 0) {
			clear();
		}
	}

	public void add(T t) {
		list.add(t);
		notifyDataSetChanged();
	}

	public void addAll(List<T> list) {
		if (list.size() < pagesize) { // 如果加载的数据量小于每页显示的数据,说明加载完成
			setIsAddOver(true);
		}
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	public void remove(T t) {
		list.remove(t);
		notifyDataSetChanged();
	}

	public void clear() {
		list.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public T getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getView(position, convertView);
	}

	public TextView getTextView(View view, int id) {
		return ViewUtil.getTextView(view, id);
	}

	public Button getButton(View view, int id) {
		return ViewUtil.getButton(view, id);
	}

	public ImageView getImageView(View view, int id) {
		return ViewUtil.getImageView(view, id);
	}

	public VideoView getVideoView(View view, int id) {
		return ViewUtil.getVideoView(view, id);
	}

	public ImageButton getImageButton(View view, int id) {
		return ViewUtil.getImageButton(view, id);
	}

	public abstract View getView(int position, View view);

	private void setIsAddOver(boolean isAddOver) {
		this.isAddOver = isAddOver;
	}

	public boolean isAddOver() {
		return isAddOver;
	}

	public void setPageSize(int pagesize) {
		this.pagesize = pagesize;
	}
}