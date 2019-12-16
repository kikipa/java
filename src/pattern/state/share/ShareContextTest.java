package pattern.state.share;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/13 9:53
 */
public class ShareContextTest {
    public static void main(String[] args) {
        ShareContext context = new ShareContext();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
    }
}
