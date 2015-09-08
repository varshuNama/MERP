package com.mcerp.asyncheck;

import java.io.IOException;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.mcerp.constant.Constant;

public class MethodSoap {

	private static final String NAMESPACE = "http://tempuri.org/";

	/************* Getting Login Detail ***************************/
	public static String getLoginDetail(String username, String password,
			String userid, String reg_id) throws XmlPullParserException,
			IOException {
		SoapObject request = new SoapObject(NAMESPACE, "ERP_User_Login_New");
		request.addProperty("strUserEmail", username);
		request.addProperty("strPassword", password);
		request.addProperty("strUserId", userid);
		request.addProperty("strRegId", reg_id);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_User_Login_New", soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Getting Leave RequestDetail ***************************/
	public static String getLeaveRequest(String empid, String empcode,
			String empname, String empemail, String office, String leavetype,
			String hdleavetype, String datefrom, String dateto, String reason)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE, "ERP_Leave_Request");
		request.addProperty("strEmpId", empid);
		request.addProperty("strEmpCode", empcode);
		request.addProperty("strEmpName", empname);
		request.addProperty("strEmpEmail", empemail);
		request.addProperty("strOffice", office);
		request.addProperty("strLeaveType", leavetype);
		request.addProperty("strHDType", hdleavetype);
		request.addProperty("strDateFrom", datefrom);
		request.addProperty("strDateTo", dateto);
		request.addProperty("strReason", reason);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Leave_Request", soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Getting Leave Request **************************/

	public static String getGetLeaveRequest(String empid)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE, "ERP_Get_Leave_Request");
		request.addProperty("strProjMgrId", empid);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Get_Leave_Request", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Getting Leave Approve Detail ***************************/
	public static String getLeaveApprove(String prjmgrid, String prjname,
			String prjemail, ArrayList<String> idall, ArrayList<String> nod,
			ArrayList<String> empname, ArrayList<String> empemail,
			ArrayList<String> empcode, ArrayList<String> empid,
			ArrayList<String> leavereson, ArrayList<String> leavefrom,
			ArrayList<String> leaveto, ArrayList<String> leavetype,
			ArrayList<String> approvereject) throws XmlPullParserException,
			IOException {
		SoapObject request = new SoapObject(NAMESPACE, "ERP_Leave_Approve");
		request.addProperty("strProjMgrId", prjmgrid);
		request.addProperty("strProjMgrName", prjname);
		request.addProperty("strProjMgrEmail", prjemail);

		addAttributeparams(request, idall, "ArrayListId_All");
		addAttributeparams(request, nod, "ArrayListNOD");
		addAttributeparams(request, empname, "ArrayListEmpName");
		addAttributeparams(request, empemail, "ArrayListEmpEmail");
		addAttributeparams(request, empcode, "ArrayListEmpCode");
		addAttributeparams(request, empid, "ArrayListEmpId");
		addAttributeparams(request, leavereson, "ArrayListLeaveReason");
		addAttributeparams(request, leavefrom, "ArrayListLeaveFrom");
		addAttributeparams(request, leaveto, "ArrayListLeaveTo");
		addAttributeparams(request, leavetype, "ArrayListLeaveType");
		addAttributeparams(request, approvereject, "ArrayListApproveReject");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Leave_Approve", soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Getting Leave Request **************************/

	public static String getGetLeaveReport(String empid, String year)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE, "ERP_Emp_Leave_Report");
		request.addProperty("strEmpId", empid);
		request.addProperty("strYear", year);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Emp_Leave_Report", soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Getting TimeSheet **************************/

	public static String getTimeSheet(String empId, String empCode,
			String monthYear, String trainerFlag)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE, "Employee_Timesheet");
		request.addProperty("strEmpCode", empCode);
		request.addProperty("strEmpId", empId);
		request.addProperty("strMonthYear", monthYear);
		request.addProperty("strTrainerFlag", trainerFlag);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/Employee_Timesheet", soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************ Submit TimeSheet **************************/

	public static String getSubmitTimesheet(String empid, String empcode,
			String empname, String empmail, String monthname, String monthyr,
			String maildesc, ArrayList<String> arr1, ArrayList<String> arr2,
			ArrayList<String> arr3, ArrayList<String> arr4,
			ArrayList<String> arr5, ArrayList<String> arr6,
			ArrayList<String> arr7, ArrayList<String> arr8,
			ArrayList<String> arr9, ArrayList<String> arr10,
			ArrayList<String> arr11, ArrayList<String> arr12,
			ArrayList<String> arr13, ArrayList<String> arr14,
			ArrayList<String> arr15, ArrayList<String> arr16,
			ArrayList<String> arr17, ArrayList<String> arr18,
			ArrayList<String> arr19, ArrayList<String> arr20,
			ArrayList<String> arr21) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"Employee_Timesheet_Create");
		request.addProperty("strEmpId", empid);
		request.addProperty("strEmpCode", empcode);
		request.addProperty("strEmpName", empname);
		request.addProperty("strEmpEmail", empmail);
		request.addProperty("strMonthName", monthname);
		request.addProperty("strMonthYear", monthyr);
		request.addProperty("strMailDesc", maildesc);

		addAttributeparams(request, arr1, "arrayTSId");
		addAttributeparams(request, arr2, "arrayProjAllocationId");
		addAttributeparams(request, arr3, "arrayProjCode");
		addAttributeparams(request, arr4, "arrayProjMgr");
		addAttributeparams(request, arr5, "arrayProjCountry");
		addAttributeparams(request, arr6, "arrayProjCircle");
		addAttributeparams(request, arr7, "arrayProjStart");
		addAttributeparams(request, arr8, "arrayProjTimesheetStart");
		addAttributeparams(request, arr9, "arrayTSDId");
		addAttributeparams(request, arr10, "arrayTSDate");
		addAttributeparams(request, arr11, "arrayTSDay");
		addAttributeparams(request, arr12, "arrayTSDAAmount");
		addAttributeparams(request, arr13, "arrayTSDACurType");
		addAttributeparams(request, arr14, "arrayTSActivity");
		addAttributeparams(request, arr15, "arrayTSDescription");
		addAttributeparams(request, arr16, "arrayTSStatus");
		addAttributeparams(request, arr17, "arrayTSDAId");
		addAttributeparams(request, arr18, "arrayTSAssetStockId");
		addAttributeparams(request, arr19, "arrayTSTrainingId");
		addAttributeparams(request, arr20, "arrayTSTimesheetType");
		addAttributeparams(request, arr21, "arrayTSLeaveNOD");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/Employee_Timesheet_Create", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Approve Timesheet *************************/

	public static String getApproveTimeSheet(String prMgId, String monthyr)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"Get_Timesheet_Approval_Summary");
		request.addProperty("strProjMgrId", prMgId);
		request.addProperty("strMonthYear", monthyr);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/Get_Timesheet_Approval_Summary",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Approve Timesheet Search *************************/

	public static String getTimeSheetApproveSearch(String prMgId, String empid,
			String monthyr) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"Get_Timesheet_Approval_Detail");
		request.addProperty("strProjMgrId", prMgId);
		request.addProperty("strEmpId", empid);
		request.addProperty("strMonthYear", monthyr);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/Get_Timesheet_Approval_Detail",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Approve Timesheet Search *************************/

	public static String getApproveRejectTimeSheet(String prMgId,
			String PrMgname, String empid, String empcode, String empname,
			String empemail, String prjdesc, String status,
			ArrayList<String> arr1, ArrayList<String> arr2)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"Employee_Timesheet_Approve_Reject");
		request.addProperty("strProjMgrId", prMgId);
		request.addProperty("strProjMgrName", PrMgname);
		request.addProperty("strEmpId", empid);
		request.addProperty("strEmpCode", empcode);
		request.addProperty("strMonthName", empname);
		request.addProperty("strEmpEmail", empemail);
		request.addProperty("strProjDesc", prjdesc);
		request.addProperty("strStatus", status);

		addAttributeparams(request, arr1, "arrayTSId");
		addAttributeparams(request, arr2, "arrayTSDId");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/Employee_Timesheet_Approve_Reject",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	public static void addAttributeparams(SoapObject soapObj,
			ArrayList<String> paramsArrayList, String objName) {

		SoapObject elementObj = new SoapObject(NAMESPACE, objName);
		for (int i = 0; i < paramsArrayList.size(); i++) {
			elementObj.addProperty("anyType", paramsArrayList.get(i));
		}
		soapObj.addSoapObject(elementObj);
	}

	/************* Approve Timesheet *************************/

	public static String getViewTimeSheetDetail(String empid, String yearmonth,
			String strflag) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"Get_Employee_Timesheet_View");
		request.addProperty("strEmpId", empid);
		request.addProperty("strYear", yearmonth);
		request.addProperty("strFlag", strflag);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/Get_Employee_Timesheet_View",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/*************************** GCM ************************/
	public static String SendPushMessage()
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE, "Notification_List");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/Notification_List", soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Assets Issued Report Search *************************/

	public static String getAssetsIssuedReport(String empId, String empCode)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE, "ERP_ASSET_Report");
		request.addProperty("strEmpId", empId);
		request.addProperty("strEmpCode", empCode);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_ASSET_Report", soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Accept Assets *************************/

	public static String getAcceptAssetList(String empid, String empcode)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Accept_Asset_Summary");
		request.addProperty("strEmpId", empid);
		request.addProperty("strEmpCode", empcode);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Accept_Asset_Summary", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Accept Assets *************************/

	public static String getAcceptAssetDetail(String id, String transferfrom,
			String flag) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Accept_Asset_Detail");
		request.addProperty("strAssetIssueId", id);
		request.addProperty("strTransferFrom", transferfrom);
		request.addProperty("strFlag", flag);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Accept_Asset_Detail", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Accept ERP_Accept_Asset_By_Employee Search *************************/

	public static String getAcceptAssetByEmployee(String empid, String empcode,
			String empname, String empemail, String trnsfrmcode,
			String trnsfrmName, String trnsfrmEmail, ArrayList<String> arr1,
			ArrayList<String> arr2, ArrayList<String> arr3,
			ArrayList<String> arr4, ArrayList<String> arr5,
			ArrayList<String> arr6, ArrayList<String> arr7,
			ArrayList<String> arr8, ArrayList<String> arr9, String flag)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Accept_Asset_By_Employee");
		request.addProperty("strEmpId", empid);
		request.addProperty("strEmpCode", empcode);
		request.addProperty("strEmpName", empname);
		request.addProperty("strEmpEmail", empemail);
		request.addProperty("strTransferFromCode", trnsfrmcode);
		request.addProperty("strTransferFromName", trnsfrmName);
		request.addProperty("strTransferFromEmail", trnsfrmEmail);

		addAttributeparams(request, arr1, "arrayCategoryName");
		addAttributeparams(request, arr2, "arrayAssetName");
		addAttributeparams(request, arr3, "arrayAssetSrNo");
		addAttributeparams(request, arr4, "arrayAssetTagNo");
		addAttributeparams(request, arr5, "arrayAssetRemarks");
		addAttributeparams(request, arr6, "arrayAssetStatus");
		addAttributeparams(request, arr7, "arrayAssetIssueId");
		addAttributeparams(request, arr8, "arrayAssetDetailId");
		addAttributeparams(request, arr9, "arrayAssetKeyId");

		request.addProperty("strFlag", flag);
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Accept_Asset_By_Employee",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/******************************** Get Transfer Assets *********************************************************/
	public static String getTransferAssets(String empid, String empcode)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_GET_ASSET_EMP_Circle_FOR_TRANSFER");
		request.addProperty("strEmpId", empid);
		request.addProperty("strEmpCode", empcode);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_GET_ASSET_EMP_Circle_FOR_TRANSFER",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/******************************** Get Transfer Assets *********************************************************/
	public static String SendDataTransferAssets(String empid, String empcode,
			String empname, String empemail, String circleid,
			String transferdate, String remarks, String circlename,
			String transfertokeyid, String transfertoname, String projcode,
			String projname, String projmgrcode, String projmgremail,
			String projmgrname, ArrayList<String> arr1, ArrayList<String> arr2)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_SEND_ASSET_TRANSFER_TO_EMP");
		request.addProperty("strEmpId", empid);
		request.addProperty("strEmpCode", empcode);
		request.addProperty("strEmpName", empname);
		request.addProperty("strEmpEmail", empemail);
		request.addProperty("strCircleId", circleid);
		request.addProperty("strAssetTransferDate", transferdate);
		request.addProperty("strTransferToKeyId", transfertokeyid);
		request.addProperty("strRemarks", remarks);
		request.addProperty("strCircleName", circlename);
		request.addProperty("strTransfereeEmpName", transfertoname);
		request.addProperty("strProjCode", projcode);
		request.addProperty("strProjName", projname);
		request.addProperty("strProjMgrCode", projmgrcode);
		request.addProperty("strProjMgrEmail", projmgremail);
		request.addProperty("strProjMgrName", projmgrname);
		request.addProperty("strTransfereeProjName", "");

		addAttributeparams(request, arr1, "arrayAssetDesc");
		addAttributeparams(request, arr2, "arrayAssetId");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_SEND_ASSET_TRANSFER_TO_EMP",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/****************************** Assets Transfer To Admin *******************************************/

	public static String SendDataTransferAssetsToAdmin(String empid,
			String empcode, String empname, String empemail, String circleid,
			String transferdate, String remarks, String circlename,
			String transfertokeyid, String projcode, String projname,
			String projmgrcode, String projmgremail, String projmgrname,
			ArrayList<String> arr1, ArrayList<String> arr2)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_SEND_ASSET_TransferToStock");
		request.addProperty("strEmpId", empid);
		request.addProperty("strEmpCode", empcode);
		request.addProperty("strEmpName", empname);
		request.addProperty("strEmpEmail", empemail);
		request.addProperty("strCircle", circleid);
		request.addProperty("strAssetTransferDate", transferdate);
		// request.addProperty("strTransferToKeyId", transfertokeyid);
		request.addProperty("strRemarks", remarks);
		request.addProperty("strCircleName", circlename);
		request.addProperty("strProjCode", projcode);
		request.addProperty("strProjName", projname);
		request.addProperty("strProjMgrCode", projmgrcode);
		request.addProperty("strProjMgrEmail", projmgremail);
		request.addProperty("strProjMgrName", projmgrname);
		// request.addProperty("strTransfereeProjName", "");

		// addAttributeparams(request, arr1, "arrayAssetDetailId");

		// addAttributeparams(request, arr3, "arrayAssetStockId");
		addAttributeparams(request, arr1, "arrayAssetDesc");
		addAttributeparams(request, arr2, "arrayAssetKeyID");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_SEND_ASSET_TransferToStock",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Getting Data from New Travel ***************************/
	public static String getNewTravelGetData(String userid)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Employee_Project_Allocation_Detail");
		request.addProperty("strEmpId", userid);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Employee_Project_Allocation_Detail",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	public static String sendNewTravelData(String userid, String empcode,
			String empname, String empemail, String projcode, String projname,
			String projmgrid, String projmgrcode, String projmgrname,
			String projmgremail, String projallocationid, String startDATE,
			String endDATE, String startTIME, String endTIME,
			String fromLOCATION, String toLOCATION, String travelAMOUNT,
			String travelKM, String remarks, String encodedImage,
			String travelmode) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Employee_Project_Travel_Entry");
		request.addProperty("strEmpId", userid);
		request.addProperty("strEmpCode", empcode);
		request.addProperty("strEmpName", empname);
		request.addProperty("strEmpEmail", empemail);
		request.addProperty("strProjCode", projcode);
		request.addProperty("strProjName", projname);
		request.addProperty("strProjMgrId", projmgrid);
		request.addProperty("strProjMgrCode", projmgrcode);
		request.addProperty("strProjMgrName", projmgrname);
		request.addProperty("strProjMgrEmail", projmgremail);
		request.addProperty("strProjAllocationId", projallocationid);
		request.addProperty("strTravelStartDate", startDATE);
		request.addProperty("strTravelEndDate", endDATE);
		request.addProperty("strTravelStartTime", startTIME);
		request.addProperty("strTravelEndTime", endTIME);
		request.addProperty("strTravelStartLocation", fromLOCATION);
		request.addProperty("strTravelEndLocation", toLOCATION);
		request.addProperty("strTravelAmount", travelAMOUNT);
		request.addProperty("strTravelKM", travelKM);
		request.addProperty("strRemarks", remarks);
		request.addProperty("strImage", encodedImage);
		request.addProperty("strTravelMode", travelmode);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Employee_Project_Travel_Entry",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/******************************** TravelExpenseReport *********************************************************/
	public static String getTravelExpenseReport(String EmpId, String MonthYear,
			String TravelMode, String Flag) throws XmlPullParserException,
			IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Employee_Project_Travel_Report");
		request.addProperty("strEmpId", EmpId);
		request.addProperty("strMonthYear", MonthYear);
		request.addProperty("strTravelMode", TravelMode);
		request.addProperty("strFlag", Flag);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(
				"http://mcpsindia.com/Android_ERP_WS/ERP.asmx");
		htse.call("http://tempuri.org/ERP_Employee_Project_Travel_Report",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/******************************** Project Travel Entry *********************************************************/
	public static String sendNewTravelData(String userid, String empcode,
			String empname, String empemail, String projcode, String projname,
			String projmgrid, String projmgrcode, String projmgrname,
			String projmgremail, String projallocationid, String startDATE,
			String endDATE, String startTIME, String endTIME,
			String fromLOCATION, String toLOCATION, String travelAMOUNT,
			String travelKM, String remarks, String encodedImage)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Employee_Project_Travel_Entry");
		request.addProperty("strEmpId", userid);// 1589
		request.addProperty("strEmpCode", empcode);// 1589
		request.addProperty("strEmpName", empname);// Akhilesh Kumar Sahu
		request.addProperty("strEmpEmail", empemail);// akhilesh.sahu@mcpsinc.com
		request.addProperty("strProjCode", projcode);// MCT0245
		request.addProperty("strProjName", projname);
		request.addProperty("strProjMgrId", projmgrid);
		request.addProperty("strProjMgrCode", projmgrcode);
		request.addProperty("strProjMgrName", projmgrname);
		request.addProperty("strProjMgrEmail", projmgremail);
		request.addProperty("strProjAllocationId", projallocationid);
		request.addProperty("strTravelStartDate", startDATE);
		request.addProperty("strTravelEndDate", endDATE);
		request.addProperty("strTravelStartTime", startTIME);
		request.addProperty("strTravelEndTime", endTIME);
		request.addProperty("strTravelStartLocation", fromLOCATION);
		request.addProperty("strTravelEndLocation", toLOCATION);
		request.addProperty("strTravelAmount", travelAMOUNT);
		request.addProperty("strTravelKM", travelKM);
		request.addProperty("strRemarks", remarks);
		request.addProperty("strImage", encodedImage);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Employee_Project_Travel_Entry",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	// method for get summary of travel
	public static String gettravelsummary(String managerid)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Employee_Project_Travel_Approval_Summary");
		request.addProperty("strProjMgrId", managerid);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call(
				"http://tempuri.org/ERP_Employee_Project_Travel_Approval_Summary",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	// method for get details of travel
	public static String gettraveldetailsforapprovel(String managerid,
			String empid) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Employee_Project_Travel_Approval_Detail");
		request.addProperty("strProjMgrId", managerid);
		request.addProperty("strEmpId", empid);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call(
				"http://tempuri.org/ERP_Employee_Project_Travel_Approval_Detail",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/**************************** Approve travel data ***********************************************/
	public static String getApproveRejectTraveldetails(String prMgId,
			String PrMgname, String managetempid, String strEmpName,
			String strEmpEmail, ArrayList<String> arr1, ArrayList<String> arr2,
			ArrayList<String> arr3, ArrayList<String> arr4,
			ArrayList<String> arr5, ArrayList<String> arr6,
			ArrayList<String> arr7, ArrayList<String> arr8,
			ArrayList<String> arr9, ArrayList<String> arr10,
			ArrayList<String> arr11) throws XmlPullParserException, IOException {

		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Employee_Project_Travel_Expense_Update");
		request.addProperty("ProjMgrId", prMgId);// 386
		request.addProperty("strProjMgrName", PrMgname);// nitish saini
		request.addProperty("strProjMgrEmail", managetempid);// amit.tonk@mcpsinc.com
		request.addProperty("strEmpName", strEmpName);// nitish saini
		request.addProperty("strEmpEmail", strEmpEmail);// nitish.saini@mcpsinc.com

		addAttributeparams(request, arr1, "arrayId");// [3, 4]
		addAttributeparams(request, arr2, "arrayProjName");// [Tools and
															// Softwares -
															// MCT0245, Tools
															// and Softwares -
															// MCT0245]
		addAttributeparams(request, arr3, "arrayLocation");// [Mumbai to Goa,
															// Punjab to Delhi]
		addAttributeparams(request, arr4, "arrayTravelDate");// [07 Jul 2015
																// Time :
																// 15:10:30 to
																// 09 Jul 2015
																// Time :
																// 13:30:30, 11
																// Jul 2015 Time
																// : 15:10:30 to
																// 11 Jul 2015
																// Time :
																// 18:30:30]
		addAttributeparams(request, arr5, "arrayUnit");// [Rupees, Rupees]
		addAttributeparams(request, arr6, "arrayUnitCost");// [500.0, 300.0]
		addAttributeparams(request, arr7, "arrayTransportMode");// [FL, FL]
		addAttributeparams(request, arr8, "arrayTransportModeDesc");// Flight,
																	// Flight]
		addAttributeparams(request, arr9, "arrayApproveUnitCost");
		addAttributeparams(request, arr10, "arrayApproveStatus");// [2,2]
		addAttributeparams(request, arr11, "arrayRemarks");// [ghn, hjh]

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call(
				"http://tempuri.org/ERP_Employee_Project_Travel_Expense_Update",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	public static String getinventorydetails(String empcode)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Asset_List_For_BarCode_Mapping");
		request.addProperty("strEmpCode", empcode);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(
				"http://mcpsindia.com/Android_ERP_WS/ERP.asmx");
		htse.call("http://tempuri.org/ERP_Asset_List_For_BarCode_Mapping",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/*
	 * .........................................................send inventory
	 * details .........................
	 */

	public static String sendInventorydetails(String strEmpId,
			ArrayList<String> arrayAssetStockId,
			ArrayList<String> arrayAssetBarCode,
			ArrayList<String> arrayAssetRemarks) throws XmlPullParserException,
			IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Asset_BarCode_Mapping");
		request.addProperty("strEmpId", strEmpId);// 1460

		addAttributeparams(request, arrayAssetStockId, "arrayAssetStockId");// 5837
		addAttributeparams(request, arrayAssetBarCode, "arrayAssetBarCode");// 6009800461091
		addAttributeparams(request, arrayAssetRemarks, "arrayAssetRemarks");// test

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Asset_BarCode_Mapping", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/****************** GTS Accept Training *********************************/

	public static String getAcceptTrainingdetails(String trainerId)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"GTS_Training_Schedule_For_Trainer_Acceptance");
		request.addProperty("strTrainerId", trainerId);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(
				"http://mcpsindia.com/Android_ERP_WS/ERP.asmx");
		htse.call(
				"http://tempuri.org/GTS_Training_Schedule_For_Trainer_Acceptance",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	public static String sendNewAcceptTrainingData(ArrayList<String> arr1,
			ArrayList<String> arr2, ArrayList<String> arr3, String empName,
			String userName, ArrayList<String> arr4, ArrayList<String> arr5,
			ArrayList<String> arr6, ArrayList<String> arr7,
			ArrayList<String> arr8, ArrayList<String> arr9,
			ArrayList<String> arr10, ArrayList<String> arr11,
			ArrayList<String> arr12, ArrayList<String> arr13)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_GTS_Training_Accept");
		addAttributeparams(request, arr1, "ArrayListTsId");
		addAttributeparams(request, arr2, "ArrayListRemarks");
		addAttributeparams(request, arr3, "ArrayListApprove");
		// addAttributeparams(request, arr4, "empName");
		request.addProperty("strEmpName", empName);
		request.addProperty("strEmpEmail", userName);
		// addAttributeparams(request, arr4, "userName");
		addAttributeparams(request, arr4, "ArrayListTraining");
		addAttributeparams(request, arr5, "ArrayListLocation");
		addAttributeparams(request, arr6, "ArrayListCustomer");
		addAttributeparams(request, arr7, "ArrayListTrainingStart");
		addAttributeparams(request, arr8, "ArrayListTrainingEnd");
		addAttributeparams(request, arr9, "ArrayListNoOfDays");
		addAttributeparams(request, arr10, "ArrayListTravelStart");
		addAttributeparams(request, arr11, "ArrayListTravelEnd");
		addAttributeparams(request, arr12, "ArrayListDA");
		addAttributeparams(request, arr13, "ArrayListEmailTo");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_GTS_Training_Accept", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************************ GTS Complete Training *************************/
	public static String getList_to_complete_training(String strEmpId)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_GTS_TrainingList_ToComplete");
		request.addProperty("strEmpId", strEmpId);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_GTS_TrainingList_ToComplete",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	public static String sendCompleteTrainingData(String strTRSId,
			String strEmpId) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_GTS_Training_Complete");
		request.addProperty("strTRSId", strTRSId);
		request.addProperty("strEmpId", strEmpId);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_GTS_Training_Complete", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/******************* Gts_View_Training_Report *************************/
	public static String getGts_View_Training_Report_Data(String strTrainerId,
			String strYearMonth) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_GTS_Training_Report_TrainerWise");
		request.addProperty("strTrainerId", strTrainerId);
		request.addProperty("strYearMonth", strYearMonth);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_GTS_Training_Report_TrainerWise",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/****************** Leave Cancel Req *********************************/

	public static String getLeaveCancelReq(String EmpId)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Leave_Cancel_GetData");
		request.addProperty("strEmpId", EmpId);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Leave_Cancel_GetData", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	public static String sendLeave_Canceal_Req(ArrayList<String> arr1,
			ArrayList<String> arr2, String userName, String empName,
			ArrayList<String> arr3, ArrayList<String> arr4,
			ArrayList<String> arr5, ArrayList<String> arr6,
			ArrayList<String> arr7) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Leave_Cancel_ReqToPm");
		addAttributeparams(request, arr1, "arrayLeaveID");
		addAttributeparams(request, arr2, "arrayCancelRemark");

		request.addProperty("strEmpEmail", userName);
		request.addProperty("strEmpName", empName);

		addAttributeparams(request, arr3, "arrayPmName");
		addAttributeparams(request, arr4, "arrayPmEmail");
		addAttributeparams(request, arr5, "arrayLeaveDate");
		addAttributeparams(request, arr6, "arrayLeaveDay");
		addAttributeparams(request, arr7, "arrayProjName");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Leave_Cancel_ReqToPm", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/****************** Leave Cancellation Proj Mgr Approval *********************************/

	public static String getLeaveCancelation_ProjMgrApprova(String strEmpId)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Leave_Cancel_ProjMgrApproval_GetData");
		request.addProperty("strEmpId", strEmpId);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call(
				"http://tempuri.org/ERP_Leave_Cancel_ProjMgrApproval_GetData",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/****************** sumbit_LeaveCancelation_ProjMgrApprova *********************************/

	public static String send_sumbit_LeaveCancelation_ProjMgrApprova(
			String empId, String empName, String userName,
			ArrayList<String> arr1, ArrayList<String> arr2,
			ArrayList<String> arr3, ArrayList<String> arr4,
			ArrayList<String> arr5, ArrayList<String> arr6,
			ArrayList<String> arr7, ArrayList<String> arr8,
			ArrayList<String> arr9, ArrayList<String> arr10)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_Leave_Cancel_Request_Approval");
		request.addProperty("strEmpID", empId);
		request.addProperty("strEmpName", empName);
		request.addProperty("strEmpEmail", userName);
		addAttributeparams(request, arr1, "arrayLeaveId");
		addAttributeparams(request, arr2, "arrayApprovalFlag");
		addAttributeparams(request, arr3, "arrayApproveReason");
		addAttributeparams(request, arr4, "arrayReqEmpId");
		addAttributeparams(request, arr5, "arrayReqEmpName");
		addAttributeparams(request, arr6, "arrayReqEmpEmail");
		addAttributeparams(request, arr7, "arrayLeaveDate");
		addAttributeparams(request, arr8, "arrayLeaveDay");
		addAttributeparams(request, arr9, "arrayProjectName");
		addAttributeparams(request, arr10, "arrayLeaveMonthYear");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_Leave_Cancel_Request_Approval",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Project Costing Get Data ***************************/
	public static String GetDataForPojectCosting()
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_ProjectCost_GetProjList");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_ProjectCost_GetProjList",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	/************* Project Costing Get Data ***************************/
	public static String SendAndGetProjectCostGetSheet(String strprjcode,
			String sheetdate) throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_ProjectCost_GetSheet");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		request.addProperty("strProjCode", strprjcode);
		request.addProperty("strSheetDate", sheetdate);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_ProjectCost_GetSheet", soapEnvelope,
				null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}

	public static String ERPProjectCostAddNewProjectedCost(String empId,
			 String strMy,String projectcode, ArrayList<String> arr1,
			ArrayList<String> arr2, ArrayList<String> arr3,
			ArrayList<String> arr4, ArrayList<String> arr5)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_ProjectCost_Add_NewProjected_Cost");

		request.addProperty("strEmpId", empId);
		request.addProperty("strProjCode", projectcode);
		request.addProperty("strMY", strMy);
		addAttributeparams(request, arr1, "arraySheetId");
		addAttributeparams(request, arr2, "arrayResourceCode");
		addAttributeparams(request, arr3, "arrayQty");
		addAttributeparams(request, arr4, "arrayPrice");
		addAttributeparams(request, arr5, "arrayTotal");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_ProjectCost_Add_NewProjected_Cost",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}
	/************* Project Costing Edit Get Data ***************************/
	public static String ERPProjectCostEditGetData(String projectcode,
			 String projectclosed,String getdate)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_ProjectCost_Edit_GetData");
		request.addProperty("strProjCode", projectcode);
		request.addProperty("strProjClosed", projectclosed);
		request.addProperty("strMY", getdate);

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_ProjectCost_Edit_GetData",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}
	
	/************* Project Costing Edit Get Data ***************************/
	public static String ERPProjectCostEditGetEditDetails(String costid,
			 String sheetid)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_ProjectCost_Edit_GetEditDetails");
		request.addProperty("strSheetId", sheetid);
		request.addProperty("strCostId", costid);
		
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_ProjectCost_Edit_GetEditDetails",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}
	
	public static String ERPProjectCostEditSubmitEditData(String costID,
			 String sheetID,String empid, ArrayList<String> arr1,
			ArrayList<String> arr2, ArrayList<String> arr3,
			ArrayList<String> arr4, ArrayList<String> arr5,ArrayList<String> arr6, ArrayList<String> arr7)
			throws XmlPullParserException, IOException {
		SoapObject request = new SoapObject(NAMESPACE,
				"ERP_ProjectCost_Edit_SubmitEditData");

		request.addProperty("strCostId", costID);
		request.addProperty("strSheetId", sheetID);
		request.addProperty("strEmpId", empid);
		addAttributeparams(request, arr1, "arrayDetailId");
		addAttributeparams(request, arr2, "arrayCostId");
		addAttributeparams(request, arr3, "arraySheetId");
		addAttributeparams(request, arr4, "arrayResourceId");
		addAttributeparams(request, arr5, "arrayQty");
		addAttributeparams(request, arr6, "arrayPrice");
		addAttributeparams(request, arr7, "arrayAmount");

		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(request);
		System.setProperty("http.keepAlive", "false");
		HttpTransportSE htse = new HttpTransportSE(Constant.BaseUrl);
		htse.call("http://tempuri.org/ERP_ProjectCost_Edit_SubmitEditData",
				soapEnvelope, null);
		SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
		return response.toString();
	}
}
