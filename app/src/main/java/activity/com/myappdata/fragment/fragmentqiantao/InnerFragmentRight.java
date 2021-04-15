package activity.com.myappdata.fragment.fragmentqiantao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.com.myappdata.R;

/***
 * fragment
 *嵌套
 */
public class InnerFragmentRight extends Fragment implements View.OnClickListener {
    private View InnerFragmentRight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreateView(inflater, container, savedInstanceState);
        InnerFragmentRight = inflater.inflate(R.layout.fragment_innerright, null);
        initfindViewById();
        return InnerFragmentRight;
    }

    private void initfindViewById() {
    }

    @Override
    public void onClick(View v) {

    }
}
