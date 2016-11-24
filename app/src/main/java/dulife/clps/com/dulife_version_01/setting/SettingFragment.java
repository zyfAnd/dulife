package dulife.clps.com.dulife_version_01.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.main.LoginActivity;

public class SettingFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
       LinearLayout mLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        mLinearLayout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.linearLayout:
            startActivity(new Intent(getContext(), LoginActivity.class));
            break;
        }
    }
}
