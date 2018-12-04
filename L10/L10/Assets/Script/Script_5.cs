using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Script_5 : MonoBehaviour {
    public float speed = 5;
    private Rigidbody rb;
    
    private Color basicColor = Color.green;
    private Color hoverColor = Color.red;
    private Color hoverColor2 = Color.blue;
    private Renderer renderer;

    public Transform gameobject;
   


    // Use this for initialization
    void Start()
    {
        rb = GetComponent<Rigidbody>();
        renderer = GetComponent<Renderer>();
        //renderer.material.color = basicColor;
       
    }

    // Update is called once per frame
    void Update()
    {
        float moveHorizontal = Input.GetAxis("Horizontal");
        float moveVertical = Input.GetAxis("Vertical");


        if (Input.GetKeyDown(KeyCode.G))
        {
            renderer.material.color = hoverColor;
        }
        if (Input.GetKeyDown(KeyCode.H))
        {
            renderer.material.color = hoverColor2;
        }
       
      
        Vector3 movement = new Vector3(moveHorizontal, 0.0f, moveVertical);
        rb.AddForce(movement * speed);

 }

     void OnCollisionEnter(Collision collision)
    {
       /* */

       /* if (collision.gameObject.name == "Blue_Cube")
        {
            rb.AddForce(0, 1000, 0);
        }
         */
        if (collision.gameObject.name == "Grenn_Cube")
        {
            Destroy(collision.gameObject);
        }

        if (collision.gameObject.name == "Red_Cube")
        {
            Destroy(this.gameObject);
        }
        Rigidbody rb = collision.gameObject.GetComponent<Rigidbody>();
        rb.AddForce(0, 1000, 0);
    }
}
