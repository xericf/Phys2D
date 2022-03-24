package model.util;

// Holds properties pertaining to the transform of an object
public class Transform {
    private Vector2 position;
    private Vector2 scale;
    private float rotation;

    // Constructor for transform
    // EFFECTS: Creates a transform object with a given position,
    // and given scale and rotation around the z axis.
    public Transform(Vector2 position, Vector2 scale, float rotation) {
        this.position = position;
        this.scale = new Vector2(1, 1);
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

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
/*
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Transform transform = (Transform) obj;
        return position.equals(transform.getPosition())
                && scale.equals(transform.getScale())
                && rotation == transform.getRotation();
    }
*/
}

