using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Script_1 : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}


    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.name == "Floor")
        {
            Debug.Log("Hit the floor");
        }
        if (collision.gameObject.name == "Wall")
        {
            Debug.Log("Hit the wall");
        }
    }
}
