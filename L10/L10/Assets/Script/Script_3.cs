using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Script_3 : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
    private void OnCollisionEnter(Collision collision)
    {
      Rigidbody rb = collision.gameObject.GetComponent<Rigidbody>();
      rb.AddForce(1000, 0, 0);
       
       
    }


}
