package pattern.state.share;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/13 9:46
 */
public abstract class ShareState {
    public abstract void handle(ShareContext context);
}
