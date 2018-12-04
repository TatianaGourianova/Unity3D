using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class Script_1 : MonoBehaviour, IPointerClickHandler
{
    private Rigidbody rb;
    private Color hoverColor = Color.red;
    private Color hoverColor2 = Color.blue;
    private Renderer renderer;
   
	// Use this for initialization
	void Start () {
        rb = GetComponent<Rigidbody>();
        renderer = GetComponent<Renderer>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}
    // Обнаружение, если происходит щелчок
    public void OnPointerClick(PointerEventData pointerEventData)
    {
        //Use this to tell when the user right-clicks on the Button
        if (pointerEventData.button == PointerEventData.InputButton.Right)
        {
            //Output to console the clicked GameObject's name and the following message. You can replace this with your own actions for when clicking the GameObject.
            Debug.Log(name + " Game Object Right Clicked!");
            renderer.material.color = hoverColor;
        }

        //Use this to tell when the user left-clicks on the Button
        if (pointerEventData.button == PointerEventData.InputButton.Left)
        {
            Debug.Log(name + " Game Object Left Clicked!");
            renderer.material.color = hoverColor2;
        }
    }

}
