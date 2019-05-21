package Engine.Event;

public class Quest implements Event{

    public Quest(Event cond_, Event then_, Event else_)
    {
        this.cond_ = cond_;
        this.then_ = then_;
        this.else_ = else_;
    }
    @Override
    public boolean run() {
        if(cond_.run())
                return then_.run();
        return else_.run();
    }

    private Event cond_;
    private Event then_;
    private Event else_;
}