package activity.com.myappdata.fragment.fg.utils;

import activity.com.myappdata.fragment.GoodsInfoFragment;

public class GlobalParms {
    private static GoodsInfoFragment GoodsInfoFragment; //主页fragment
//    private static CommunityFragment communityFragment; //公告fragment
//    private static BookMallFragment bookMallFragment; //书城fragment
//    private static UserFragment userFragment; //个人fragment
//    public static SkipFragment mSkipFragment; //改变选中Frangment的接口

    /**
     * 获取主页Fragment
     *
     * @return
     */
    public static GoodsInfoFragment getBookrackFragment() {
        if (GoodsInfoFragment == null) {
            GoodsInfoFragment = new GoodsInfoFragment();
        }
        return GoodsInfoFragment;
    }

//    /**
//     * 公告fragment
//     *
//     * @return
//     */
//    public static CommunityFragment getCommunityFragment() {
//        if (communityFragment == null) {
//            communityFragment = new CommunityFragment();
//        }
//        return communityFragment;
//    }
//
//    /**
//     * 书城fragment
//     *
//     * @return
//     */
//    public static BookMallFragment getBookMallFragment() {
//        if (bookMallFragment == null) {
//            bookMallFragment = new BookMallFragment();
//        }
//        return bookMallFragment;
//    }
//
//    /**
//     * 个人fragment
//     *
//     * @return
//     */
//    public static UserFragment getUserFragment() {
//        if (userFragment == null) {
//            userFragment = new UserFragment();
//        }
//        return userFragment;
//    }
//
//    /**
//     * 设置被选中的Fragment
//     *
//     * @param skipFragment
//     */
    public static void setFragmentSelected(GoodsInfoFragment mGoodsInfoFragment) {
        mGoodsInfoFragment = GoodsInfoFragment;
    }
}