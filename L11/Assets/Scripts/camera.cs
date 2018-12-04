using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class camera : MonoBehaviour
{

    private Transform cameraTransform;

    //for applyForce 
    public int force = 2000;

    //movement speed 
    public float speed = 10;

    public float mouseSensitivity = 100.0f;
    public float clampAngle = 80.0f;

    private float rotY = 0.0f; // rotation around the up/y axis 
    private float rotX = 0.0f; // rotation around the right/x axis 

    void Start()
    {
        cameraTransform = Camera.main.transform;

        Vector3 rot = transform.localRotation.eulerAngles;
        rotY = rot.y;
        rotX = rot.x;
    }

    void Update()
    {
        applyForce();
        moveKeys();

        float mouseX = Input.GetAxis("Mouse X");
        float mouseY = -Input.GetAxis("Mouse Y");

        rotY += mouseX * mouseSensitivity * Time.deltaTime;
        rotX += mouseY * mouseSensitivity * Time.deltaTime;

        rotX = Mathf.Clamp(rotX, -clampAngle, clampAngle);

        Quaternion localRotation = Quaternion.Euler(rotX, rotY, 0.0f);
        transform.rotation = localRotation;
    }

    public void moveKeys()
    {
        if (Input.GetKey(KeyCode.DownArrow))
        {
            transform.position += cameraTransform.forward * (Time.deltaTime * speed) * -1;

        }
        if (Input.GetKey(KeyCode.UpArrow))
        {
            transform.position += cameraTransform.forward * (Time.deltaTime * speed);

        }
    }

    public void applyForce()
    {

        if (Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);//ожидаем точку в виде пикселей, определить объект под указателем мышки
            if (Physics.Raycast(ray, out hit, Mathf.Infinity))
            {

                if (hit.rigidbody)
                    hit.rigidbody.AddForceAtPosition(force * transform.forward, hit.point);
            }
        }
    }
}