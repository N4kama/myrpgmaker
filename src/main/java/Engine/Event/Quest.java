package Engine.Event;

public class Quest implements GameEvents{

    public Quest(GameEvents cond_, GameEvents then_, GameEvents else_)
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

    private GameEvents cond_;
    private GameEvents then_;
    private GameEvents else_;
}
