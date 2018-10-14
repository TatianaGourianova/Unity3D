package io.github.kotelliada.lab3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.OpenCVLoader
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {
    private lateinit var cannyMat: Mat
    private lateinit var grayMat: Mat
    private lateinit var sobelMat: Mat
    private lateinit var rgbaMat: Mat
    private lateinit var laplacianMat: Mat

    private lateinit var gradX: Mat
    private lateinit var absGradX: Mat
    private lateinit var gradY: Mat
    private lateinit var absGradY: Mat
    private lateinit var grad: Mat

    private lateinit var selectedFilter: Filters

    init {
        OpenCVLoader.initDebug()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraView.visibility = SurfaceView.VISIBLE
        cameraView.setCvCameraViewListener(this)

        filtersGroup.setOnCheckedChangeListener(filtersGroupListener)

        if (savedInstanceState == null) {
            filtersGroup.check(R.id.noFiltersButton)
        }
    }

    override fun onResume() {
        super.onResume()
        cameraView?.apply { enableView() }
    }

    override fun onCameraViewStarted(width: Int, height: Int) {
        rgbaMat = Mat(width, height, CvType.CV_8UC4)
        cannyMat = Mat()
        grayMat = Mat()
        sobelMat = Mat()
        gradX = Mat()
        absGradX = Mat()
        gradY = Mat()
        absGradY = Mat()
        laplacianMat = Mat()
        grad = Mat()
    }

    override fun onCameraViewStopped() {
        rgbaMat.release()
        cannyMat.release()
        grayMat.release()
        sobelMat.release()
        gradX.release()
        absGradX.release()
        gradY.release()
        absGradY.release()
        laplacianMat.release()
        grad.release()
    }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame): Mat {
        rgbaMat = inputFrame.rgba()

        return when (selectedFilter) {
            Filters.SOBEL -> {
                Imgproc.cvtColor(rgbaMat, grayMat, Imgproc.COLOR_BGR2GRAY)
                Imgproc.Sobel(grayMat, gradX, CvType.CV_16S, 1, 0, 3, 1.0, 0.0)
                Imgproc.Sobel(grayMat, gradY, CvType.CV_16S, 0, 1, 3, 1.0, 0.0)
                Core.convertScaleAbs(gradX, absGradX)
                Core.convertScaleAbs(gradY, absGradY)
                Core.addWeighted(absGradX, 0.5, absGradY, 0.5, 1.0, sobelMat)
                sobelMat
            }
            Filters.CANNY -> {
                Imgproc.cvtColor(rgbaMat, grayMat, Imgproc.COLOR_BGR2GRAY)
                Imgproc.Canny(grayMat, cannyMat, 10.0, 100.0)
                cannyMat
            }
            Filters.LAPLACE -> {
                Imgproc.cvtColor(rgbaMat, grayMat, Imgproc.COLOR_BGR2GRAY)
                Imgproc.Laplacian(grayMat, grad, CvType.CV_16S, 3, 1.0, 0.0, Core.BORDER_DEFAULT)
                Core.convertScaleAbs(grad, laplacianMat)
                laplacianMat
            }
            else -> rgbaMat
        }
    }

    override fun onPause() {
        super.onPause()
        cameraView?.apply { disableView() }
    }

    private val filtersGroupListener = RadioGroup.OnCheckedChangeListener { _, checkedId ->
        selectedFilter = when (checkedId) {
            R.id.sobelOperatorButton -> Filters.SOBEL
            R.id.lapaceOperatorButton -> Filters.LAPLACE
            R.id.cannyEdgesButton -> Filters.CANNY
            R.id.noFiltersButton -> Filters.NO_FILTER
            else -> throw IllegalArgumentException()
        }
    }
}