package com.yuncommunity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yuncommunity.app.PersonInfo;
import com.yuncommunity.base.BaseActivity;
import com.yuncommunity.util.ETUtil;
import com.yuncommunity.util.JSONUtil;
import com.yuncommunity.util.NetUtil.RequestStringListener;

/**
 * 修改密码
 * 
 * @author oldfeel
 * 
 */
public class ChangePassword extends BaseActivity implements OnClickListener {
	private EditText etPassword, etPassword1, etPassword2;
	private Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_password);
		setTitle("修改密码");
		initView();
		initListener();
	}

	private void initView() {
		etPassword = getEditText(R.id.change_password_0);
		etPassword1 = getEditText(R.id.change_password_1);
		etPassword2 = getEditText(R.id.change_password_2);
		btnSubmit = getButton(R.id.change_password_submit);
	}

	private void initListener() {
		btnSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.change_password_submit:
			submit();
			break;
		default:
			break;
		}
	}

	private void submit() {
		if (isEmpty(etPassword)) {
			etPassword.setError("必填");
			return;
		}
		if (isEmpty(etPassword1)) {
			etPassword1.setError("必填");
			return;
		}
		if (isEmpty(etPassword2)) {
			etPassword2.setError("必填");
			return;
		}
		if (!ETUtil.isEquals(etPassword1, etPassword2)) {
			etPassword2.setError("两次密码不同");
			return;
		}
		if (!PersonInfo.getInstance(getApplicationContext()).getPassword()
				.equals(getString(etPassword))) {
			etPassword.setError("密码错误");
			return;
		}
		PersonInfo.getInstance(getApplicationContext()).setPassword(
				getString(etPassword1));
		PersonInfo.update(ChangePassword.this, "正在修改密码",
				new RequestStringListener() {

					@Override
					public void onComplete(String result) {
						if (JSONUtil.isSuccess(result)) {
							showToast("修改成功");
							finish();
						} else {
							showToast("修改失败," + JSONUtil.getMessage(result));
						}
					}
				});
	}
}