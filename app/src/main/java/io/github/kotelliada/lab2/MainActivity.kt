package io.github.kotelliada.lab2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.OpenCVLoader
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

class MainActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {
    private lateinit var argbMat: Mat
    private lateinit var grayMat: Mat
    private lateinit var cannyMat: Mat
    private lateinit var gaussianBlurMat: Mat
    private lateinit var medianBlurMat: Mat
    private lateinit var blurMat: Mat
    private var currentFilter = Filters.NO_FILTERS

    init {
        OpenCVLoader.initDebug()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        camera.visibility = SurfaceView.VISIBLE
        val cameraLayoutParams = camera.layoutParams
        cameraLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        cameraLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        camera.layoutParams = cameraLayoutParams
        camera.setCvCameraViewListener(this)
    }

    override fun onResume() {
        super.onResume()
        camera?.enableView()
    }

    override fun onPause() {
        super.onPause()
        camera?.disableView()
    }

    override fun onCameraViewStarted(width: Int, height: Int) {
        argbMat = Mat(width, height, CvType.CV_8UC4)
        gaussianBlurMat = Mat(width, height, CvType.CV_8UC4)
        medianBlurMat = Mat(width, height, CvType.CV_8UC4)
        grayMat = Mat()
        cannyMat = Mat()
        blurMat = Mat()
    }

    override fun onCameraViewStopped() {
        argbMat.release()
        grayMat.release()
        cannyMat.release()
        gaussianBlurMat.release()
        medianBlurMat.release()
        blurMat.release()
    }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame): Mat {
        argbMat = inputFrame.rgba()

        return when (currentFilter) {
            Filters.NO_FILTERS -> argbMat
            Filters.CANNY -> {
                Imgproc.cvtColor(argbMat, grayMat, Imgproc.COLOR_BGR2GRAY)
                Imgproc.Canny(grayMat, cannyMat, 10.0, 100.0)
                cannyMat
            }
            Filters.BLUR -> {
                Imgproc.blur(argbMat, blurMat, Size(3.0, 3.0))
                blurMat
            }
            Filters.GAUSSIAN_BLUR -> {
                Imgproc.GaussianBlur(argbMat, gaussianBlurMat, Size(5.0, 5.0), 0.0)
                gaussianBlurMat
            }
            Filters.MEDIAN_BLUR -> {
                Imgproc.medianBlur(argbMat, medianBlurMat, 5)
                medianBlurMat
            }
            Filters.BOX_FILTER -> {
                Imgproc.boxFilter(argbMat, argbMat, -1, Size(3.0, 3.0))
                argbMat
            }
            Filters.EROSION_FILTER -> {
                val kernelErode = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, Size(5.0, 5.0))
                Imgproc.erode(argbMat, argbMat, kernelErode)
                argbMat
            }
            Filters.DILATE -> {
                val kernelDilate = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(3.0, 3.0))
                Imgproc.dilate(argbMat, argbMat, kernelDilate)
                argbMat
            }
        }
    }

    fun filterClick(view: View) {
        currentFilter = when (view.id) {
            R.id.noFiltersButton -> Filters.NO_FILTERS
            R.id.cannyFilterButton -> Filters.CANNY
            R.id.blurFilterButton -> Filters.BLUR
            R.id.gaussianBlurButton -> Filters.GAUSSIAN_BLUR
            R.id.medianBlurButton -> Filters.MEDIAN_BLUR
            R.id.boxFilterButton -> Filters.BOX_FILTER
            R.id.erosionFilterButton -> Filters.EROSION_FILTER
            R.id.dilateFilterButton -> Filters.DILATE
            else -> throw UnsupportedOperationException()
        }
    }
}