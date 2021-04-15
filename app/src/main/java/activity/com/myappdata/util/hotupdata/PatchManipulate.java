package activity.com.myappdata.util.hotupdata;

import android.content.Context;

import com.meituan.robust.Patch;

import java.util.List;

public abstract class PatchManipulate {

    protected abstract List<Patch> fetchPatchList(Context context);
    protected abstract boolean verifyPatch(Context context, Patch patch);
    protected abstract boolean ensurePatchExist(Patch patch);
}
