package net.mingsoft.cms.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mingsoft.base.constant.Const;
import net.mingsoft.basic.util.BasicUtil;

/**
 * 上传文件
 */
@Api("上传文件接口")
@Controller
@RequestMapping("/common/file")
public class FileCommonAction {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	private static final int BUFFER_SIZE = 100 * 1024;
	private static final String TEMP = "/temp";

	/**
	 * 上传路径
	 */
	@Value("${ms.upload.path}")
	private String uploadFloderPath;

	@Value("${ms.upload.max-size}")
	private int uploadMaxSize;

	@Value("${ms.upload.allowed}")
	private String uploadFileAllowed;

	@Value("${ms.upload.denied}")
	private String uploadFileDenied;

	@Value("${ms.upload.memory-size}")
	private int uploadMaxInMemorySize;

	@Value("${ms.upload.distPath}")
	private String uploadDistPath;



	/**
	 * 处理post请求上传文件
	 *
	 * @param req
	 *            HttpServletRequest对象
	 * @param res
	 *            HttpServletResponse 对象
	 * @throws ServletException
	 *             异常处理
	 * @throws IOException
	 *             异常处理
	 */
	@ApiOperation(value = "处理post请求上传文件")
	@PostMapping("/upload")
	@ResponseBody
	public void upload(HttpServletRequest req, HttpServletResponse res) {
		LOG.info("FileCommonAction========upload");
		res.setContentType("text/html;charset=utf-8");
		Integer chunk = 0, chunks = 0; // 分块上传
		try {
			PrintWriter out = res.getWriter();
			String uploadPath = "";
			String floderName = uploadFloderPath + Const.SEPARATOR + BasicUtil.getAppId();
			String uploadFolder = uploadDistPath + Const.SEPARATOR + BasicUtil.getAppId()+ Const.SEPARATOR; // 上传的文件路径
			//String uploadFolder = BasicUtil.getRealPath(floderName) + Const.SEPARATOR; // 上传的文件路径
			String isRename = req.getParameter("isRename");// 是否重命名 true:重命名
			String name = req.getParameter("name");// 固定文件名称
			String _tempPath = req.getServletContext().getRealPath(TEMP);// 存放文件的临时目录路径
			LOG.info("FileCommonAction========upload,_tempPath:{}",_tempPath);
			File file = new File(_tempPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			File tempPath = new File(_tempPath); // 用于存放临时文件的目录
			FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(req.getServletContext());
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setFileCleaningTracker(fileCleaningTracker);
			// maximum size that will be stored in memory
			// 允许设置内存中存储数据的门限，单位：字节
			factory.setSizeThreshold(uploadMaxInMemorySize);
			// the location for saving data that is larger than
			// getSizeThreshold()
			// 如果文件大小大于SizeThreshold，则保存到临时目录
			factory.setRepository(tempPath);

			ServletFileUpload upload = new ServletFileUpload(factory);


			// maximum size before a FileUploadException will be thrown
			try {
				List fileItems = upload.parseRequest(req);

				Iterator iter = fileItems.iterator();

				// 正则匹配，过滤路径取文件名
				String regExp = ".+\\\\(.+)$";

				// 过滤掉的文件类型
				String[] errorType = uploadFileDenied.split(",");
				String fileName = "";
				String diyPath = "";
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					InputStream input = item.getInputStream();
					String fieldName = item.getFieldName();
					if (fieldName.equals("uploadFloderPath")) {
						uploadFloderPath = Streams.asString(input);
						//uploadFolder = BasicUtil.getRealPath(uploadFloderPath); // 上传的文件路径
						uploadFolder = uploadFloderPath; // 上传的文件路径
						if (!StringUtils.isEmpty(uploadPath)) {
							uploadFolder += uploadPath;
						}
						//自定义路径
						if(!StringUtils.isEmpty(diyPath)){
							uploadFolder = diyPath;
						}
						floderName = uploadFloderPath;
						LOG.info("uploadPath:" + uploadFloderPath);
					}
					if (fieldName.equals("uploadPath")) {
						uploadPath = Streams.asString(input);
						uploadFolder += uploadPath;
						LOG.info("uploadPath:" + uploadFolder);
					} else if (fieldName.equals("diyPath")) {
						diyPath = Streams.asString(input);
						uploadFolder = Streams.asString(input);
						LOG.info("diyPath:" + uploadFolder);
					} else if (fieldName.equals("isRename")) {
						isRename = Streams.asString(input);
						LOG.info("isRename:" + isRename);
					} else if (fieldName.equals("name")) {
						name = Streams.asString(input);
						LOG.info("name:" + name);
					} else if (fieldName.equals("maxSize")) {
						uploadMaxSize = Integer.parseInt(item.getString()) * 1024 * 1024;
						LOG.info("maxSize:" + uploadMaxSize);
					} else if (fieldName.equals("allowedFile")) {
						uploadFileAllowed = item.getString();
						LOG.info("uploadFileAllowed:" + uploadFileAllowed);

					} else if (fieldName.equals("deniedFile")) {
						LOG.info("uploadFileDenied:" + uploadFileDenied);
						uploadFileDenied = item.getString();
					} else if ("chunk".equals(fieldName)) {
						chunk = Integer.valueOf(Streams.asString(input));
						LOG.info("chunk:" + chunk);
					} else if ("chunks".equals(fieldName)) {
						chunks = Integer.valueOf(Streams.asString(input));
						LOG.info("chunks:" + chunks);
					} else if ("name".equals(fieldName)) {
						fileName = new String(item.get(), "UTF-8");
						LOG.info("name:" + fileName);
					} else if (!item.isFormField()) { // 忽略其他不是文件域的所有表单信息
						if (StringUtils.isEmpty(fileName)) {
							fileName = item.getName();
						}
						long size = item.getSize();
						if ((fileName == null || fileName.equals("")) && size == 0)
							continue;
						try {
							// 最大上传文件，单位：字节 1000000/1024=0.9M
							upload.setSizeMax(uploadMaxSize);

							// 保存上传的文件到指定的目录
							// 在下文中上传文件至数据库时，将对这里改写
							// 自定义路径放在c盘根目录可能会出现权限错误
							String folder = uploadFolder + Const.SEPARATOR + TEMP;
							if(!StringUtils.isEmpty(diyPath)){
								folder = uploadFolder;
							}
							File _file = new File(folder);
							if (!_file.exists()) {
								_file.mkdirs();
							}
							File destFile = new File(folder, fileName);
							// //文件已存在删除旧文件（上传了同名的文件）
							if (chunk == 0 && destFile.exists()) {
								destFile.delete();
								destFile = new File(folder, fileName);
							}
							// 合成文件
							appendFile(input, destFile);
							LOG.info("chunk:{},chunks:{},uploadFolder:{},floderName:{},diyPath:{}", chunk,chunks,uploadFolder,floderName,diyPath);
							if (chunk == chunks - 1) {
								String _fileName = fileName;
								// // 重命名
								if (StringUtils.isEmpty(isRename) || Boolean.parseBoolean(isRename)) {
									_fileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("."));
									destFile.renameTo(new File(uploadFolder, _fileName));
								} else if (!StringUtils.isEmpty(name)) {
									_fileName = name + fileName.substring(fileName.indexOf("."));
									destFile.renameTo(new File(uploadFolder, _fileName));
								} else {
									// savePath += fileName;
									// outPath += fileName;
									destFile.renameTo(new File(uploadFolder, _fileName));
								}
								LOG.info("上传完成1");
								if (uploadPath.equals("/") || StringUtils.isEmpty(uploadPath)) {
									out.print(floderName + Const.SEPARATOR + _fileName);
									//自定义盘符
								}  else if(StringUtils.isNotEmpty(diyPath)){
									out.print(destFile);
								} else {
									out.print(floderName + Const.SEPARATOR + uploadPath + Const.SEPARATOR + _fileName);
								}
								new File(folder).delete();
							} else if (chunks == 0) {
								String _fileName = fileName;
								if (!StringUtils.isEmpty(name)) {
									_fileName = name + fileName.substring(fileName.indexOf("."));
								}
								if (StringUtils.isEmpty(isRename) || Boolean.parseBoolean(isRename)) {
									_fileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("."));
								}

								destFile.renameTo(new File(uploadFolder, _fileName));
								new File(folder).delete();
								if (uploadPath.equals("/") || StringUtils.isEmpty(uploadPath)) {
									out.print(floderName + Const.SEPARATOR + _fileName);
									//自定义盘符
								} else if(StringUtils.isNotEmpty(diyPath)){
									out.print(destFile);
								} else {
									//floderName
									out.print(floderName + Const.SEPARATOR + uploadPath + Const.SEPARATOR + _fileName);
								}
								LOG.info("上传完成2");
							} else {
								LOG.info("还剩[" + (chunks - 1 - chunk) + "]个块文件");

							}

							LOG.debug("upload file ok return path " + uploadFolder + fileName);

							try {
								if (null != input) {
									input.close();
								}
								if (null != out) {
									out.flush();
									out.close();
								}
							} catch (IOException e) {
								LOG.error(e.getMessage());
							}
						} catch (Exception e) {
							e.printStackTrace();
							this.LOG.debug(e.getMessage());
						}

					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
				this.LOG.debug(e.getMessage());
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void appendFile(InputStream in, File destFile) {
		OutputStream out = null;
		try {
			// plupload 配置了chunk的时候新上传的文件append到文件末尾
			if (destFile.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(destFile, true), BUFFER_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(destFile), BUFFER_SIZE);
			}
			in = new BufferedInputStream(in, BUFFER_SIZE);

			int len = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
	}
}
