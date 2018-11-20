using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using UnityEngine;

public class CubeGenerator: MonoBehaviour
{
    float minX, minZ, maxX, maxZ;

    void Start()
    {
        MeshRenderer render = gameObject.GetComponent<MeshRenderer>();
        minX = render.bounds.min.x;
        maxX = render.bounds.max.x;

        minZ = render.bounds.min.z;
        maxZ = render.bounds.max.z;
    }

    void Update()
    {
        float newX = UnityEngine.Random.Range(minX, maxX);
        float newZ = UnityEngine.Random.Range(minZ, maxZ);
        float newY = gameObject.transform.position.y + 5;

        if (Input.GetMouseButtonDown(0))
        {
            GameObject cubb = GameObject.CreatePrimitive(PrimitiveType.Sphere);
            cubb.transform.position = new Vector3(newX, newY, newZ);

            cubb.AddComponent<Rigidbody>();
        }


    }


}
