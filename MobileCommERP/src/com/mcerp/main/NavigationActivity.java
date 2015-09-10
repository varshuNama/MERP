package com.mcerp.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.assets.AcceptAssets;
import com.mcerp.assets.AssetReport;
import com.mcerp.assets.Inventorydetails;
import com.mcerp.assets.TransferAssets;
import com.mcerp.asyncheck.AsyncTaskDataLogin;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.constant.Constant;
import com.mcerp.fragments.ApplyLeave;
import com.mcerp.fragments.Home;
import com.mcerp.gts.Accept_Training;
import com.mcerp.gts.Complete_Training;
import com.mcerp.gts.Gts_View_Training_Report;
import com.mcerp.model.HomeModel;
import com.mcerp.notification.InboxNotificationActivity;
import com.mcerp.notification.WriteNotificationFragment;
import com.mcerp.projectedcosting.Projected_Costing_Edit_Frament;
import com.mcerp.projectedcosting.Projected_New_Fragment;
import com.mcerp.travel.ApproveTravelView;
import com.mcerp.travel.NewTravel;
import com.mcerp.travel.TravelExpensesReportFragment;
import com.mcerp.travel.TravelExpensesTable_Report;
import com.mcerp.util.AppPreferences;
import com.mcerp.util.Utility;

public class NavigationActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	ImageView home;
	android.support.v4.app.Fragment fragment = null;
	TextView appname, titletext, UserNameText;
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	String response;

	ConnectionDetector connection;
	ExpandableListView expListView;
	ArrayList<HomeModel> homearray = new ArrayList<HomeModel>();
	HashMap<String, List<String>> listDataChild;
	ExpandableListAdapter listAdapter;
	List<String> listDataHeader;
	String Message = null;
	AppPreferences prefs;
	LinearLayout linaermain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation);
		init();
		setUpDrawer();
	}

	private void init() {

		Utility.initFonts(getApplicationContext());
		prefs = AppPreferences.getInstance(NavigationActivity.this);
		connection = new ConnectionDetector(NavigationActivity.this);
		UserNameText = (TextView) findViewById(R.id.UserNameText);
		linaermain = (LinearLayout) findViewById(R.id.mainlenaer);
		home = (ImageView) findViewById(R.id.home);
		titletext = (TextView) findViewById(R.id.textTitle);
		if (!prefs.getRegistered()) {
			prefs.setRegistered(true);

		}
		response = prefs.getResponse();
		parseResponse();
		titletext.setText("Home");
		UserNameText.setText(prefs.getEmpName());
		linaermain.setOnClickListener(homeOnclickListener);

	}

	private void setUpDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerLayout.setDrawerListener(mDrawerListener);
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		prepareListData();
		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);
		expListView.setAdapter(listAdapter);
		fragment = new Home(homearray);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		mDrawerLayout.closeDrawer(expListView);

		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			int previousitem = -1;

			@Override
			public void onGroupExpand(int groupPosition) {
				if (groupPosition != previousitem) {
					expListView.collapseGroup(previousitem);
					previousitem = groupPosition;
				}

			}
		});
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				switch (groupPosition) {
				case 0:
					fragment = new Home(homearray);
					new AsyncTaskDataLogin(NavigationActivity.this, prefs
							.getUserName(), prefs.getPassword(), prefs
							.getUserID(), prefs.getGCMRegID()).execute("");

					break;

				case 10:
					Log.d("Resnsjc", prefs.getRegistered() + "");
					prefs.setRegistered(false);
					prefs.setUserID("");
					prefs.setUserName("");
					prefs.setPassword("");
					prefs.setEmpCode("");
					prefs.setEmpName("");
					prefs.setResponse("");
					prefs.setMonthName("");
					prefs.setProjectMgr("");
					prefs.setMonthYear("");
					prefs.setReportingMgr("");

					Intent intent = new Intent(NavigationActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();

				default:
					break;
				}
				return false;
			}
		});
		expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				switch (groupPosition) {
				/***************************************** Leave ************************************************************************/
				case 1:

					switch (childPosition) {
					case 0:
						fragment = new ApplyLeave();
						titletext.setText("Apply Leave");
						break;
					case 1:
						if (connection.isConnectingToInternet()) {
							Intent intent1 = new Intent(
									NavigationActivity.this, ApproveLeave.class);
							startActivity(intent1);
							finish();

						} else {
							alertForInternetNotAvail();
						}
						break;

					case 2:

						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									LeaveCancealReq.class);
							startActivity(intent);
							finish();
						} else {
							alertForInternetNotAvail();
						}
						break;
					case 3:

						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									LeaveCancelation_ProjMgrApprova.class);
							startActivity(intent);
							finish();
						} else {
							alertForInternetNotAvail();
						}
						break;
					case 4:
						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									LeaveReport.class);
							startActivity(intent);
							finish();
						} else {
							alertForInternetNotAvail();
						}
						break;
					default:
						break;
					}
					break;
				/***************************************** TimeSheet ************************************************************************/
				case 2:
					switch (childPosition) {
					case 0:
						if (connection.isConnectingToInternet()) {

							Intent intent = new Intent(NavigationActivity.this,
									NewSendTimesheet.class);
							startActivity(intent);
							finish();

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 1:
						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									ApproveTimeSheet.class);
							startActivity(intent);
							finish();
						} else {
							alertForInternetNotAvail();
						}

						break;
					case 2:
						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									ViewTimeSheet.class);
							startActivity(intent);
							finish();

						} else {
							alertForInternetNotAvail();
						}
						break;
					default:
						break;
					}
					break;

				/***************************************** Assets ************************************************************************/

				case 3:
					switch (childPosition) {
					case 0:
						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									AcceptAssets.class);
							startActivity(intent);
							finish();
						} else {
							alertForInternetNotAvail();
						}

						break;
					case 1:
						if (connection.isConnectingToInternet()) {

							fragment = new TransferAssets(
									NavigationActivity.this);
							titletext.setText("Asset Transfer");

						}

						break;
					case 2:
						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									Inventorydetails.class);
							startActivity(intent);

							finish();
						} else {
							alertForInternetNotAvail();
						}
						break;

					case 3:
						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									AssetReport.class);
							startActivity(intent);

							finish();
						} else {
							alertForInternetNotAvail();
						}
						break;

					default:
						break;
					}
					break;
				/******************************************** Project Travel ***************************************************************/

				case 4:
					switch (childPosition) {
					case 0:
						if (connection.isConnectingToInternet()) {
							fragment = new NewTravel();
							titletext.setText("New");

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 1:
						if (connection.isConnectingToInternet()) {
							titletext.setText("Travel Approve");
							Intent i = new Intent(NavigationActivity.this,
									ApproveTravelView.class);
							startActivity(i);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 2:
						if (connection.isConnectingToInternet()) {
							fragment = new TravelExpensesReportFragment();
							getSupportFragmentManager().beginTransaction()
									.replace(R.id.content_frame, fragment)
									.commit();
							titletext.setText("Travel Expenses View");
							mDrawerLayout.closeDrawer(expListView);

						} else {
							alertForInternetNotAvail();
						}
						break;

					default:
						break;
					}
					break;

				/***************************************** GTS ************************************************************************/

				case 5:
					switch (childPosition) {
					case 0:
						if (connection.isConnectingToInternet()) {

							Intent i = new Intent(NavigationActivity.this,
									Accept_Training.class);

							startActivity(i);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 1:
						if (connection.isConnectingToInternet()) {

							Intent i = new Intent(NavigationActivity.this,
									Complete_Training.class);

							startActivity(i);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 2:
						if (connection.isConnectingToInternet()) {

							fragment = new com.mcerp.gts.Gts_View_Training_Fragment();
							titletext.setText("GTS View");

						} else {
							alertForInternetNotAvail();
						}
						break;

					default:
						break;
					}
					break;
				/***************************************** Projected Costing *******************************************************************/

				case 6:
					switch (childPosition) {

					case 0:
						if (connection.isConnectingToInternet()) {

							fragment = new Projected_New_Fragment();
							titletext.setText("Projected Costing New");

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 1:
						if (connection.isConnectingToInternet()) {

							fragment = new Projected_Costing_Edit_Frament();
							titletext.setText("Edit Projected Costing");

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 2:
						if (connection.isConnectingToInternet()) {

						} else {
							alertForInternetNotAvail();
						}
						break;

					default:
						break;
					}
					break;

				/***************************************** Reports ************************************************************************/

				case 7:
					switch (childPosition) {
					case 0:
						if (connection.isConnectingToInternet()) {

							Intent i = new Intent(NavigationActivity.this,
									LeaveReport.class);

							startActivity(i);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 1:
						if (connection.isConnectingToInternet()) {

							Intent i = new Intent(NavigationActivity.this,
									ViewTimeSheet.class);

							startActivity(i);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 2:
						if (connection.isConnectingToInternet()) {

							Intent i = new Intent(NavigationActivity.this,
									AssetReport.class);

							startActivity(i);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 3:
						if (connection.isConnectingToInternet()) {

							Intent i = new Intent(NavigationActivity.this,
									TravelExpensesTable_Report.class);

							startActivity(i);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 4:
						if (connection.isConnectingToInternet()) {

							Intent i = new Intent(NavigationActivity.this,
									Gts_View_Training_Report.class);

							startActivity(i);

						} else {
							alertForInternetNotAvail();
						}

					default:
						break;
					}
					break;

				/***************************************** Notification ************************************************************************/

				case 8:
					switch (childPosition) {
					case 0:
						if (connection.isConnectingToInternet()) {

							if (prefs.getUserID().equals("17")
									|| prefs.getUserID().equals("386") ||  prefs.getUserID().equals("1494") ) {

								fragment = new WriteNotificationFragment();
								getSupportFragmentManager().beginTransaction()
										.replace(R.id.content_frame, fragment)
										.commit();
								titletext.setText("Write Notification");
								mDrawerLayout.closeDrawer(expListView);
							} else {
								new SweetAlertDialog(NavigationActivity.this,
										SweetAlertDialog.NORMAL_TYPE)
										.setTitleText("Sorry")
										.setContentText(
												"you are not authorized to compose message.")
										.setConfirmText("ok")
										.setConfirmClickListener(
												new SweetAlertDialog.OnSweetClickListener() {
													@Override
													public void onClick(
															SweetAlertDialog sDialog) {

														sDialog.dismiss();
													}
												}).show();

							}

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 1:
						if (connection.isConnectingToInternet()) {

							Intent intent = new Intent(NavigationActivity.this,
									InboxNotificationActivity.class);
							startActivity(intent);

						} else {
							alertForInternetNotAvail();
						}

						break;

					default:
						break;
					}
					break;

				/***************************************** HR Policies ************************************************************************/

				case 9:
					switch (childPosition) {
					case 0:
						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									PDFActivity.class);
							intent.putExtra("URL",
									Constant.Human_Resource_Policy_Manual);
							intent.putExtra("FILENAME",
									"HumanResourcePolicyManual");
							startActivity(intent);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 1:

						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									PDFActivity.class);
							intent.putExtra("URL", Constant.Holiday_List);
							intent.putExtra("FILENAME", "HolidayList");
							startActivity(intent);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 2:

						if (connection.isConnectingToInternet()) {

							Intent intent = new Intent(NavigationActivity.this,
									PDFActivity.class);
							intent.putExtra("URL",
									Constant.Guidlines_For_filling_Claim_forms);
							intent.putExtra("FILENAME",
									"GuidlinesForfillingClaimforms");
							startActivity(intent);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 3:

						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									PDFActivity.class);
							intent.putExtra("URL",
									Constant.Local_Conveyance_Form);
							intent.putExtra("FILENAME", "LocalConveyanceForm");
							startActivity(intent);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 4:

						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									PDFActivity.class);
							intent.putExtra("URL",
									Constant.Project_Advance_Request_Form);
							intent.putExtra("FILENAME",
									"Project_Advance_Request_Form");
							startActivity(intent);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 5:

						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									PDFActivity.class);
							intent.putExtra("URL", Constant.Domestic_Claim_Form);
							intent.putExtra("FILENAME", "DomesticClaimForm");
							startActivity(intent);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 6:

						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									PDFActivity.class);
							intent.putExtra("URL",
									Constant.International_Exp_Claim_Form);
							intent.putExtra("FILENAME",
									"InternationalExpClaimForm");
							startActivity(intent);

						} else {
							alertForInternetNotAvail();
						}

						break;
					case 7:

						if (connection.isConnectingToInternet()) {
							Intent intent = new Intent(NavigationActivity.this,
									PDFActivity.class);
							intent.putExtra("URL",
									Constant.NOC_for_Resign_Employee);
							intent.putExtra("FILENAME", "NOCforResignEmployee");
							startActivity(intent);

						} else {
							alertForInternetNotAvail();
						}
						break;

					default:
						break;
					}

				default:
					break;
				}
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
				mDrawerLayout.closeDrawer(expListView);
				return false;
			}
		});
	}

	View.OnClickListener homeOnclickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (mDrawerLayout.isDrawerOpen(expListView)) {
				mDrawerLayout.closeDrawer(expListView);
			} else {
				mDrawerLayout.openDrawer(expListView);
			}
		}
	};

	private DrawerListener mDrawerListener = new DrawerListener() {
		@Override
		public void onDrawerStateChanged(int status) {

		}

		@Override
		public void onDrawerSlide(View view, float slideArg) {

		}

		@Override
		public void onDrawerOpened(View view) {
		}

		@Override
		public void onDrawerClosed(View view) {
		}
	};

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Home");
		listDataHeader.add("Leave");
		listDataHeader.add("Time Sheet");
		listDataHeader.add("Asset Tracker");
		listDataHeader.add("Project Travel");
		listDataHeader.add("Global Training Schedule");
		listDataHeader.add("Pojected Cost");
		listDataHeader.add("Reports");
		listDataHeader.add("Notifications");
		listDataHeader.add("HR Policies");

		listDataHeader.add("Logout");

		// Adding child data
		List<String> Leave_Portal_list = new ArrayList<String>();
		Leave_Portal_list.add("Apply");
		Leave_Portal_list.add("Approve");
		Leave_Portal_list.add("Leave Cancel Req");
		Leave_Portal_list.add("Approve/Cancel Req");
		Leave_Portal_list.add("View");

		List<String> Time_Sheet_Portallist = new ArrayList<String>();
		Time_Sheet_Portallist.add("New/Send");
		Time_Sheet_Portallist.add("Approve");
		Time_Sheet_Portallist.add("View");

		List<String> Asset_Tracker_Portallist = new ArrayList<String>();
		Asset_Tracker_Portallist.add("Accept");
		Asset_Tracker_Portallist.add("Transfer");
		Asset_Tracker_Portallist.add("Barcode Mapping");
		Asset_Tracker_Portallist.add("View");

		List<String> Project_Travel = new ArrayList<String>();
		Project_Travel.add("New");
		Project_Travel.add("Approve");
		Project_Travel.add("View");

		List<String> Global_Training_Schedule = new ArrayList<String>();
		Global_Training_Schedule.add("Accept Training");
		Global_Training_Schedule.add("Complete Training");
		Global_Training_Schedule.add("View");
		List<String> projected_cost = new ArrayList<String>();
		if (prefs.getReportingMgr().equals("Y")) {
			projected_cost.add("New");
			projected_cost.add("Edit");
			projected_cost.add("Report");
		}

		List<String> hr_policies_list = new ArrayList<String>();
		hr_policies_list.add("Human Resource Policy Manua");
		hr_policies_list.add("Holiday List");
		hr_policies_list.add("Guidlines For filling Claim forms");
		hr_policies_list.add("Local Conveyance Form");
		hr_policies_list.add("Project Advance Request Form");
		hr_policies_list.add("Domestic Claim Form");
		hr_policies_list.add("International Exp Claim Form");
		hr_policies_list.add("NOC for Resign Employee");

		List<String> notifications_result = new ArrayList<String>();
		notifications_result.add("Compose");
		notifications_result.add("Inbox");

		List<String> Reports = new ArrayList<String>();
		Reports.add("Leave Report");
		Reports.add("Time Sheet Report");
		Reports.add("Asset Report");
		Reports.add("Project Travel Report");
		Reports.add("GTS Report");
		Reports.add("Project Cost Report");

		List<String> wk = new ArrayList<String>();
		listDataChild.put(listDataHeader.get(0), wk); // Header,
		listDataChild.put(listDataHeader.get(1), Leave_Portal_list);
		listDataChild.put(listDataHeader.get(2), Time_Sheet_Portallist);
		listDataChild.put(listDataHeader.get(3), Asset_Tracker_Portallist);
		listDataChild.put(listDataHeader.get(4), Project_Travel);
		listDataChild.put(listDataHeader.get(5), Global_Training_Schedule);
		listDataChild.put(listDataHeader.get(6), projected_cost);
		listDataChild.put(listDataHeader.get(7), Reports);
		listDataChild.put(listDataHeader.get(8), notifications_result);
		listDataChild.put(listDataHeader.get(9), hr_policies_list);

		listDataChild.put(listDataHeader.get(10), wk);

	}

	public class ExpandableListAdapter extends BaseExpandableListAdapter {

		private Context _context;
		private List<String> _listDataHeader; // header titles
		// child data in format of header title, child title
		private HashMap<String, List<String>> _listDataChild;

		public ExpandableListAdapter(Context context,
				List<String> listDataHeader,
				HashMap<String, List<String>> listChildData) {
			this._context = context;
			this._listDataHeader = listDataHeader;
			this._listDataChild = listChildData;
		}

		@Override
		public Object getChild(int groupPosition, int childPosititon) {
			return this._listDataChild.get(
					this._listDataHeader.get(groupPosition))
					.get(childPosititon);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			final String childText = (String) getChild(groupPosition,
					childPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_item, null);
			}

			TextView txtListChild = (TextView) convertView
					.findViewById(R.id.lblListItem);
			txtListChild.setTypeface(Utility.Ascom_Zwo_BoldPS);
			txtListChild.setText(childText);
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return this._listDataChild.get(
					this._listDataHeader.get(groupPosition)).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return this._listDataHeader.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return this._listDataHeader.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String headerTitle = (String) getGroup(groupPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_group, null);
			}

			TextView lblListHeader = (TextView) convertView
					.findViewById(R.id.lblListHeader);
			lblListHeader.setTypeface(Utility.Ascom_Zwo_BoldPS);
			lblListHeader.setText(headerTitle);

			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}

	private void parseResponse() {
		try {
			JSONObject jsonobj = new JSONObject(response);
			Message = jsonobj.getString("message");
			if (Message.equals("success")) {
				String arrData = jsonobj.getString("DataArr");
				JSONArray jsonarray = new JSONArray(arrData);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject json = jsonarray.getJSONObject(i);
					HomeModel modeldata = new HomeModel();
					modeldata.setUserId(json.getString("UserId"));
					modeldata.setEmpCode(json.getString("EmpCode"));
					modeldata.setReportingManager(json
							.getString("ReportingMgr"));
					modeldata.setEmail(json.getString("Email"));
					modeldata.setEmpName(json.getString("EmpName"));
					modeldata.setProject(json.getString("Project"));
					modeldata.setProjMgr(json.getString("ProjMgr"));
					modeldata.setLocation(json.getString("Location"));
					modeldata.setProjStart(json.getString("ProjStart"));
					modeldata.setProjEnd(json.getString("ProjEnd"));
					modeldata.setTotalAssets(json.getString("TotalAsset"));
					modeldata.setFromAdminPending(json
							.getString("FromAdminPending"));
					modeldata.setFromEmpPending(json
							.getString("FromEmpPending"));
					modeldata.setToEmpPending(json.getString("ToEmpPending"));
					modeldata.setToAdminPending(json
							.getString("ToAdminPending"));
					modeldata.setAssetName(json.getString("AssetName"));
					modeldata.setCategoryName(json.getString("CategoryName"));
					modeldata.setAsset_SrNo(json.getString("Asset_SrNo"));
					modeldata.setAsset_TagNo(json.getString("Asset_TagNo"));
					modeldata.setAssetIssueDate(json
							.getString("AssetIssueDate"));
					modeldata.setAcceptStatus(json.getString("AcceptStatus"));
					modeldata.setTransferToAdmin(json
							.getString("TransferToAdmin"));
					modeldata.setAdminTransferDate(json
							.getString("AdminTransferDate"));
					modeldata.setTransferToEmp(json.getString("TransferToEmp"));
					modeldata.setTransferTo(json.getString("TransferTo"));
					modeldata.setTransferDate(json.getString("TransferDate"));
					modeldata.setLeaveReq(json.getString("LeaveReq"));
					modeldata.setTimesheetReq(json.getString("TimesheetReq"));
					modeldata.setTrainingReq(json.getString("TrainingReq"));
					modeldata.setTraining(json.getString("Training"));
					modeldata.setTrainerFlag(json.getString("TrainerFlag"));
					modeldata.setTopMgtFlag(json.getString("TopMgtFlag"));
					modeldata.setMonthName(json.getString("MonthName"));
					modeldata.setMonthYear(json.getString("MonthYear"));
					homearray.add(modeldata);
					prefs.setUserID(homearray.get(0).getUserId());
					prefs.setUserName(homearray.get(0).getEmail());
					prefs.setEmpCode(homearray.get(0).getEmpCode());
					prefs.setEmpName(homearray.get(0).getEmpName());
					prefs.setMonthName(homearray.get(0).getMonthName());
					prefs.setMonthYear(homearray.get(0).getMonthYear());
					prefs.setProjectMgr(homearray.get(0).getProjMgr());
					prefs.setReportingMgr(homearray.get(0)
							.getReportingManager());

				}
			} else {
				new SweetAlertDialog(NavigationActivity.this,
						SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText(
								"Some Problem in fetching data . Sorry for inconvenience !")
						.show();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void alertForInternetNotAvail() {
		new SweetAlertDialog(NavigationActivity.this,
				SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
				.setContentText("Not Connected To Internet").show();

	}

	@Override
	public void onBackPressed() {

		AppPreferences mAppPreferences = AppPreferences
				.getInstance(NavigationActivity.this);
		if (mAppPreferences.getScreen() == com.mcerp.constant.AppConstants.homeScreen) {
			new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
					.setTitleText("Exit ?")
					.setContentText("Do you want to exit.")
					.setCancelText("No,cancel plz!")
					.setConfirmText("Yes,exit it!")
					.showCancelButton(true)
					.setCancelClickListener(
							new SweetAlertDialog.OnSweetClickListener() {
								@Override
								public void onClick(SweetAlertDialog sDialog) {
									sDialog.dismiss();
								}
							})
					.setConfirmClickListener(
							new SweetAlertDialog.OnSweetClickListener() {
								@Override
								public void onClick(SweetAlertDialog sDialog) {
									NavigationActivity.super.onBackPressed();
									sDialog.dismiss();

								}
							}).show();
		} else {

			fragment = new Home(homearray);
			titletext.setText("Home");
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, fragment).commit();
			mDrawerLayout.closeDrawer(expListView);

		}

	}
}
