package com.bullet.game.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btCylinderShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody.btRigidBodyConstructionInfo;
import com.badlogic.gdx.utils.Disposable;
import com.bullet.game.BulletPhysicsSystem;
import com.bullet.game.MotionState;

import net.mgsx.gltf.loaders.glb.GLBLoader;
import net.mgsx.gltf.scene3d.scene.Scene;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class Player implements Disposable, AnimationController.AnimationListener {
    private SceneAsset sceneAsset;
    private Scene scene;
    private ModelInstance player;
    private Vector3 playerPosition;
    private BoundingBox boundingBox;
    private btCollisionShape shape;
    private float mass = 1f;
    private Vector3 localInertia;
    private btRigidBody.btRigidBodyConstructionInfo info;
    private btRigidBody body;
    private MotionState motionState;
    private Matrix4 playerTransform;

    public Player() {
        sceneAsset = new GLBLoader().load(Gdx.files.internal("Druid.glb"));
        scene = new Scene(sceneAsset.scene);
        player = scene.modelInstance;
        
        shape = new btCylinderShape(new Vector3(0.5f, 0.5f, 0.5f));

        playerTransform = new Matrix4();
        playerTransform.setTranslation(1, MathUtils.random(10, 20), 0);
        playerTransform.rotate(new Quaternion(Vector3.Z, MathUtils.random(0f, 270f)));
        player.transform = playerTransform;
        mass = 1f;
        localInertia = new Vector3();
        shape.calculateLocalInertia(mass, localInertia);

        info = new btRigidBodyConstructionInfo(mass, null, shape, localInertia);
        body = new btRigidBody(info);

        motionState = new MotionState(playerTransform);
        body.setMotionState(motionState);
        
    }

    public void update() {

    }

    public void addBody(BulletPhysicsSystem bulletPhysicsSystem){
        bulletPhysicsSystem.addBody(body);
    }

    public ModelInstance getModelInstance() {
        return player;
    }

    @Override
    public void dispose() {
        sceneAsset.dispose();
    }

    @Override
    public void onEnd(AnimationController.AnimationDesc animation) {

    }

    @Override
    public void onLoop(AnimationController.AnimationDesc animation) {

    }
}
