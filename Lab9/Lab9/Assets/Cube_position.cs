using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using UnityEngine;

public class Cube_position: MonoBehaviour
{
    public float speed;

    void Update() {

        float posX = transform.position.x;
        float posY = transform.position.y;
        float posZ = transform.position.z;

        transform.position = new Vector3(posX + speed*Time.deltaTime, posY, posZ);

    }

}