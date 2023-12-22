package com.app.mobile_ecommerece.ui.Admin.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.loader.content.CursorLoader
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.common.EventObserver
import com.app.mobile_ecommerece.databinding.FragmentCreateProductAdminBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.model.Spec
import com.app.mobile_ecommerece.viewmodels.AdminViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@AndroidEntryPoint
class FragmentAdminCreateProduct : BaseFragment<FragmentCreateProductAdminBinding>(true) {

    private val adminviewModel: AdminViewModel by activityViewModels()
    private val REQUEST_CODE_IMG = 101
    private var selectImgUri: Uri? = null
    private var mUri: Uri? = null
    private var selectImgUri2: Uri? = null
    private var mUri2: Uri? = null
    private var selectImgUri3: Uri? = null
    private var mUri3: Uri? = null
    private var imageSelect: Int = 0;
    private lateinit var roomAdapter: ArrayAdapter<String>
    private var roomData: List<RoomModel> = listOf()
    private lateinit var categoryAdapter: ArrayAdapter<String>
    private var categoryData: List<CategoryModel> = listOf()
    var roomId = ""
    var categoryId = ""

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateProductAdminBinding {
        return FragmentCreateProductAdminBinding.inflate(inflater, container, false)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminviewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminviewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminviewModel, viewLifecycleOwner)
    }

    private fun setUpBtn() {
        binding.img1.setOnClickListener {
            setupPermissions(1)
        }
        binding.img2.setOnClickListener {
            setupPermissions(2)
        }
        binding.img3.setOnClickListener {
            setupPermissions(3)
        }
        binding.imageButton2.setOnClickListener {
            navigateBack()
        }
        binding.btnEditSave.setOnClickListener {
            createProduct()
        }
    }

    private fun setupRecycleViewLayout() {
        roomAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtRoom.setAdapter(roomAdapter)
        binding.autoCompleteTxtRoom.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            for(item in roomData) {
                if (item.nameRoom == itemSelect) {
                    roomId = item._id
                    Log.d("TAG1", roomId)
                    adminviewModel.getCategoryByRoom(item._id)
                }
            }
        }
        categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtCategory.setAdapter(categoryAdapter)
        binding.autoCompleteTxtCategory.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            for(item in categoryData) {
                if (item.nameCate == itemSelect) {
                    categoryId = item._id
                    Log.d("TAG2", categoryId)

                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adminViewModel = adminviewModel
        observerEvent()
        setupRecycleViewLayout()
        setUpBtn()
        adminviewModel.roomsData.observe(viewLifecycleOwner) { newItem ->
            val itemNames = newItem.map { it.nameRoom }
            roomAdapter.clear()
            roomAdapter.addAll(itemNames)
            roomAdapter.notifyDataSetChanged()
            roomData = newItem
        }
        adminviewModel.categoriesData.observe(viewLifecycleOwner) { newItem ->
            val itemNames = newItem.map { it.nameCate }
            categoryAdapter.clear()
            categoryAdapter.addAll(itemNames)
            categoryAdapter.notifyDataSetChanged()
            categoryData = newItem
        }
        adminviewModel.getALlRoom()
    }
    private fun setupPermissions(index: Int) {
        val permission = ContextCompat.checkSelfPermission(requireContext(),
            android.Manifest.permission.READ_EXTERNAL_STORAGE)

        val permissionWrite = ContextCompat.checkSelfPermission(requireContext(),
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED || permissionWrite != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
        else{
            openImageChooser(index)
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_CODE_IMG)
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_IMG -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i("TAG", "Permission has been denied by user")
                } else {
                    Log.i("TAG", "Permission has been granted by user")
                }
            }
        }
    }
    private fun openImageChooser(index: Int) {
//        oldRemember = userViewModel.getRemember()
//        userViewModel.setRemember(true)
        imageSelect = index;
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val minTypes = arrayOf("image/jpg")
            it.putExtra(Intent.EXTRA_MIME_TYPES, minTypes)
            startActivityForResult(it, REQUEST_CODE_IMG)
        }
    }

    private fun createProduct(){
        Log.d("TAG", "11111alo")
        val multipartBodyPartList = mutableListOf<MultipartBody.Part>()
        if(mUri != null){
            val uri = Uri.parse(mUri.toString())
            val path = RealPathUtil.getRealPath(requireContext(), uri)
            val file = createFileFromRealPath(path)
            val multipartBodyPart = createMultipartBodyPart(file!!, "images")
            multipartBodyPartList.add(multipartBodyPart)
        }
        if(mUri2 != null){
            val uri = Uri.parse(mUri2.toString())
            val path = RealPathUtil.getRealPath(requireContext(), uri)
            val file = createFileFromRealPath(path)
            val multipartBodyPart = createMultipartBodyPart(file!!, "images")
            multipartBodyPartList.add(multipartBodyPart)
        }
        if(mUri3 != null){
            val uri = Uri.parse(mUri3.toString())
            val path = RealPathUtil.getRealPath(requireContext(), uri)
            val file = createFileFromRealPath(path)
            val multipartBodyPart = createMultipartBodyPart(file!!, "images")
            multipartBodyPartList.add(multipartBodyPart)
        }
        val code = binding.etCode.text.toString()
        val name = binding.etName.text.toString()
        val description = binding.etDescription.text.toString()
        val shortDescription = binding.etShortDescription.text.toString()
        val price = binding.etPrice.text.toString().toInt()
        val quantity = binding.etQuantity.text.toString().toInt()
        val dimensions = Spec("Dimensions", binding.etDimensions.text.toString())
        val collection = Spec("Collection", binding.etCollection.text.toString())
        val materials = Spec("Materials", binding.etMaterials.text.toString())
        val specArray = listOf(dimensions, collection, materials)
        val specsJSONString = Json.encodeToString(specArray)
        val images = multipartBodyPartList.toString().toRequestBody()
        val specs = specsJSONString.toRequestBody("application/json".toMediaTypeOrNull())
        adminviewModel.createProduct(multipartBodyPartList
            , code.toRequestBody("text/plain".toMediaTypeOrNull())
            , name.toRequestBody("text/plain".toMediaTypeOrNull())
            , description.toRequestBody("text/plain".toMediaTypeOrNull())
            , shortDescription.toRequestBody("text/plain".toMediaTypeOrNull())
            , categoryId.toRequestBody("text/plain".toMediaTypeOrNull())
            , roomId.toRequestBody("text/plain".toMediaTypeOrNull())
            , specs
            , price
            , quantity)
    }

    fun createFileFromRealPath(realPath: String?): File? {
        if (realPath == null) {
            return null
        }
        return try {
            File(realPath)
        } catch (e: Exception) {
            null
        }
    }

    fun createRequestBody(file: File): RequestBody {
        return RequestBody.create(file.toURI().toURL().toString().toMediaTypeOrNull(), file)
    }

    fun createMultipartBodyPart(file: File, fieldName: String): MultipartBody.Part {
        val requestBody = createRequestBody(file)
        return MultipartBody.Part.createFormData(fieldName, file.name, requestBody)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_IMG -> {
                    when(imageSelect){
                        1 -> {
                            selectImgUri = data?.data
                            mUri = selectImgUri
                            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, selectImgUri)
                            binding.img1.setImageBitmap(bitmap)
                            Log.d("uri", selectImgUri.toString())
                        }
                        2 -> {
                            selectImgUri2 = data?.data
                            mUri2 = selectImgUri2
                            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, selectImgUri2)
                            binding.img2.setImageBitmap(bitmap)
                            Log.d("uri", selectImgUri2.toString())
                        }
                        3 -> {
                            selectImgUri3 = data?.data
                            mUri3 = selectImgUri3
                            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, selectImgUri3)
                            binding.img3.setImageBitmap(bitmap)
                            Log.d("uri", selectImgUri3.toString())
                        }
                    }
                }
            }
        }
    }
    object RealPathUtil {

        fun getRealPath(context: Context, fileUri: Uri): String? {
            // SDK >= 11 && SDK < 19
            return if (Build.VERSION.SDK_INT < 19) {
                getRealPathFromURIAPI11to18(context, fileUri)
            } else {
                getRealPathFromURIAPI19(context, fileUri)
            }// SDK > 19 (Android 4.4) and up
        }

        @SuppressLint("NewApi")
        fun getRealPathFromURIAPI11to18(context: Context, contentUri: Uri): String? {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            var result: String? = null

            val cursorLoader = CursorLoader(context, contentUri, proj, null, null, null)
            val cursor = cursorLoader.loadInBackground()

            if (cursor != null) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                result = cursor.getString(columnIndex)
                cursor.close()
            }
            return result
        }

        /**
         * Get a file path from a Uri. This will get the the path for Storage Access
         * Framework Documents, as well as the _data field for the MediaStore and
         * other file-based ContentProviders.
         *
         * @param context The context.
         * @param uri     The Uri to query.
         * @author Niks
         */
        @SuppressLint("NewApi")
        fun getRealPathFromURIAPI19(context: Context, uri: Uri): String? {

            val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

            // DocumentProvider
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]

                    if ("primary".equals(type, ignoreCase = true)) {
                        return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                    }
                } else if (isDownloadsDocument(uri)) {
                    var cursor: Cursor? = null
                    try {
                        cursor = context.contentResolver.query(uri, arrayOf(MediaStore.MediaColumns.DISPLAY_NAME), null, null, null)
                        cursor!!.moveToNext()
                        val fileName = cursor.getString(0)
                        val path = Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName
                        if (!TextUtils.isEmpty(path)) {
                            return path
                        }
                    } finally {
                        cursor?.close()
                    }
                    val id = DocumentsContract.getDocumentId(uri)
                    if (id.startsWith("raw:")) {
                        return id.replaceFirst("raw:".toRegex(), "")
                    }
                    val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads"), java.lang.Long.valueOf(id))

                    return getDataColumn(context, contentUri, null, null)
                } else if (isMediaDocument(uri)) {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]

                    var contentUri: Uri? = null
                    when (type) {
                        "image" -> contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        "video" -> contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                        "audio" -> contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }

                    val selection = "_id=?"
                    val selectionArgs = arrayOf(split[1])

                    return getDataColumn(context, contentUri, selection, selectionArgs)
                }// MediaProvider
                // DownloadsProvider
            } else if ("content".equals(uri.scheme!!, ignoreCase = true)) {

                // Return the remote address
                return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(context, uri, null, null)
            } else if ("file".equals(uri.scheme!!, ignoreCase = true)) {
                return uri.path
            }// File
            // MediaStore (and general)

            return null
        }

        /**
         * Get the value of the data column for this Uri. This is useful for
         * MediaStore Uris, and other file-based ContentProviders.
         *
         * @param context       The context.
         * @param uri           The Uri to query.
         * @param selection     (Optional) Filter used in the query.
         * @param selectionArgs (Optional) Selection arguments used in the query.
         * @return The value of the _data column, which is typically a file path.
         * @author Niks
         */
        private fun getDataColumn(context: Context, uri: Uri?, selection: String?,
                                  selectionArgs: Array<String>?): String? {

            var cursor: Cursor? = null
            val column = "_data"
            val projection = arrayOf(column)

            try {
                cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
                if (cursor != null && cursor.moveToFirst()) {
                    val index = cursor.getColumnIndexOrThrow(column)
                    return cursor.getString(index)
                }
            } finally {
                cursor?.close()
            }
            return null
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is ExternalStorageProvider.
         */
        private fun isExternalStorageDocument(uri: Uri): Boolean {
            return "com.android.externalstorage.documents" == uri.authority
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is DownloadsProvider.
         */
        private fun isDownloadsDocument(uri: Uri): Boolean {
            return "com.android.providers.downloads.documents" == uri.authority
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is MediaProvider.
         */
        private fun isMediaDocument(uri: Uri): Boolean {
            return "com.android.providers.media.documents" == uri.authority
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is Google Photos.
         */
        private fun isGooglePhotosUri(uri: Uri): Boolean {
            return "com.google.android.apps.photos.content" == uri.authority
        }
    }
}