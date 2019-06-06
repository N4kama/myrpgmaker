package Engine.Character;

import java.util.ArrayList;

import java.awt.*;
import java.awt.image.BufferedImage;
import Utils.SpriteTools;

public class EngineSprite {
    private BufferedImage spritePlayer;
    private ArrayList<BufferedImage> walkingLeft;
    private ArrayList<BufferedImage> walkingRight;
    private ArrayList<BufferedImage> walkingUp;
    private ArrayList<BufferedImage> walkingDown;
    private Animation walkLeft;
    private Animation walkRight;
    private Animation walkUp;
    private Animation walkDown;
    private Animation standLeft;
    private Animation standRight;
    private Animation standUp;
    private Animation standDown;
    private Animation curAnim;
    private String path;

    public EngineSprite(String p)
    {
        path = p;
        this.walkingLeft = new ArrayList<>();
        this.walkingRight = new ArrayList<>();
        this.walkingUp = new ArrayList<>();
        this.walkingDown = new ArrayList<>();
        spritePlayer = SpriteTools.openObject(path);
        SpriteTools.setSpriteMove(spritePlayer, walkingLeft, "left");
        SpriteTools.setSpriteMove(spritePlayer, walkingRight, "right");
        SpriteTools.setSpriteMove(spritePlayer, walkingUp, "up");
        SpriteTools.setSpriteMove(spritePlayer, walkingDown, "down");
        walkLeft = new Animation(walkingLeft, 3);
        walkRight = new Animation(walkingRight, 3);
        walkUp = new Animation(walkingUp, 3);
        walkDown = new Animation(walkingDown, 3);
        ArrayList<BufferedImage> tmp = new ArrayList<>();
        tmp.add(SpriteTools.getStandingSprite(spritePlayer, 1, 0));
        standDown = new Animation(tmp, 3);

        tmp.remove(0);
        tmp.add(SpriteTools.getStandingSprite(spritePlayer, 1, 3));
        standUp = new Animation(tmp, 3);

        tmp.remove(0);
        tmp.add(SpriteTools.getStandingSprite(spritePlayer, 1, 1));
        standLeft = new Animation(tmp, 3);

        tmp.remove(0);
        tmp.add(SpriteTools.getStandingSprite(spritePlayer, 1, 2));
        standRight = new Animation(tmp, 3);

        curAnim = standDown;
    }

    private void loadMoves() {
        
    }

    /**
     * @return BufferedImage return the spritePlayer
     */
    public BufferedImage getSpritePlayer() {
        return spritePlayer;
    }

    /**
     * @param spritePlayer the spritePlayer to set
     */
    public void setSpritePlayer(BufferedImage spritePlayer) {
        this.spritePlayer = spritePlayer;
    }

    /**
     * @return ArrayList<BufferedImage> return the walkingLeft
     */
    public ArrayList<BufferedImage> getWalkingLeft() {
        return walkingLeft;
    }

    /**
     * @param walkingLeft the walkingLeft to set
     */
    public void setWalkingLeft(ArrayList<BufferedImage> walkingLeft) {
        this.walkingLeft = walkingLeft;
    }

    /**
     * @return ArrayList<BufferedImage> return the walkingRight
     */
    public ArrayList<BufferedImage> getWalkingRight() {
        return walkingRight;
    }

    /**
     * @param walkingRight the walkingRight to set
     */
    public void setWalkingRight(ArrayList<BufferedImage> walkingRight) {
        this.walkingRight = walkingRight;
    }

    /**
     * @return ArrayList<BufferedImage> return the walkingUp
     */
    public ArrayList<BufferedImage> getWalkingUp() {
        return walkingUp;
    }

    /**
     * @param walkingUp the walkingUp to set
     */
    public void setWalkingUp(ArrayList<BufferedImage> walkingUp) {
        this.walkingUp = walkingUp;
    }

    /**
     * @return ArrayList<BufferedImage> return the walkingDown
     */
    public ArrayList<BufferedImage> getWalkingDown() {
        return walkingDown;
    }

    /**
     * @param walkingDown the walkingDown to set
     */
    public void setWalkingDown(ArrayList<BufferedImage> walkingDown) {
        this.walkingDown = walkingDown;
    }

    /**
     * @return Animation return the walkLeft
     */
    public Animation getWalkLeft() {
        return walkLeft;
    }

    /**
     * @param walkLeft the walkLeft to set
     */
    public void setWalkLeft(Animation walkLeft) {
        this.walkLeft = walkLeft;
    }

    /**
     * @return Animation return the walkRight
     */
    public Animation getWalkRight() {
        return walkRight;
    }

    /**
     * @param walkRight the walkRight to set
     */
    public void setWalkRight(Animation walkRight) {
        this.walkRight = walkRight;
    }

    /**
     * @return Animation return the walkUp
     */
    public Animation getWalkUp() {
        return walkUp;
    }

    /**
     * @param walkUp the walkUp to set
     */
    public void setWalkUp(Animation walkUp) {
        this.walkUp = walkUp;
    }

    /**
     * @return Animation return the walkDown
     */
    public Animation getWalkDown() {
        return walkDown;
    }

    /**
     * @param walkDown the walkDown to set
     */
    public void setWalkDown(Animation walkDown) {
        this.walkDown = walkDown;
    }

    /**
     * @return Animation return the standLeft
     */
    public Animation getStandLeft() {
        return standLeft;
    }

    /**
     * @param standLeft the standLeft to set
     */
    public void setStandLeft(Animation standLeft) {
        this.standLeft = standLeft;
    }

    /**
     * @return Animation return the standRight
     */
    public Animation getStandRight() {
        return standRight;
    }

    /**
     * @param standRight the standRight to set
     */
    public void setStandRight(Animation standRight) {
        this.standRight = standRight;
    }

    /**
     * @return Animation return the standUp
     */
    public Animation getStandUp() {
        return standUp;
    }

    /**
     * @param standUp the standUp to set
     */
    public void setStandUp(Animation standUp) {
        this.standUp = standUp;
    }

    /**
     * @return Animation return the standDown
     */
    public Animation getStandDown() {
        return standDown;
    }

    /**
     * @param standDown the standDown to set
     */
    public void setStandDown(Animation standDown) {
        this.standDown = standDown;
    }

    /**
     * @return Animation return the curAnim
     */
    public Animation getCurAnim() {
        return curAnim;
    }

    /**
     * @param curAnim the curAnim to set
     */
    public void setCurAnim(Animation curAnim) {
        this.curAnim = curAnim;
    }

    /**
     * @return String return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

}