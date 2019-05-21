package Engine.Npc;

public class Dialogue {

    public Dialogue(String content) {
        following = null;
        msg = content;
    }

    private String msg;
    private Dialogue following;

    /**
     * @return String return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return Dialogue return the following
     */
    public Dialogue getFollowing() {
        return following;
    }

    /**
     * @param following the following to set
     */
    public void setFollowing(Dialogue following) {
        this.following = following;
    }

}