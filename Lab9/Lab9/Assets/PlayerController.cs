using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour {

    private Transform myTransform;

    private float speed = 5;
    private Rigidbody rb;
    private Renderer rn;
    public GameObject cube;

    // Use this for initialization
    void Start() {

        rb = GetComponent<Rigidbody>();
        rn = GetComponent<Renderer>();

    }

    // Update is called once per frame
    void Update() {
        if (Input.GetKey(KeyCode.W))
            rn.material.color = Color.white;
        if (Input.GetKeyDown(KeyCode.R))
            rn.material.color = Color.red;
        if (Input.GetKeyDown(KeyCode.G))
            rn.material.color = Color.green;

        float moveHorizontal = 0.0f;
        float moveVertical = 0.0f;
        if (Input.GetKeyDown(KeyCode.RightArrow))
            moveHorizontal = 5.0f;
        if (Input.GetKeyDown(KeyCode.LeftArrow))
            moveHorizontal = -5.0f;
        if (Input.GetKeyDown(KeyCode.UpArrow))
            moveVertical = 5.0f;
        if (Input.GetKeyDown(KeyCode.DownArrow))
            moveVertical = -5.0f;

        Vector3 movement = new Vector3(moveHorizontal, 0.0f, moveVertical);
        rb.AddForce(movement * speed);

        float posX = transform.position.x;
        float posY = transform.position.y + 5;
        float posZ = transform.position.z;

        Vector3 newPos = new Vector3(posX, posY, posZ);

        cube.transform.position = Vector3.MoveTowards(cube.transform.position, newPos , Time.deltaTime);

    }
}
