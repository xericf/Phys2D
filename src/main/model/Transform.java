package model;

public class Transform {
    private Vector2 position;
    private Vector2 scale;
    private Vector2 rotation;

    public Transform(Vector2 position) {
        this.position = position;
        this.scale = new Vector2(1, 1);
        this.rotation = new Vector2(0, 0);
    }

    public Transform(Vector2 position, Vector2 scale, Vector2 rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }


    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public Vector2 getRotation() {
        return rotation;
    }

    public void setRotation(Vector2 rotation) {
        this.rotation = rotation;
    }
}
