package org.firstinspires.ftc.robotcontroller.internal.eventloop

import android.app.Activity
import com.qualcomm.ftccommon.CommandList
import com.qualcomm.ftccommon.FtcEventLoop
import com.qualcomm.ftccommon.ProgrammingModeController
import com.qualcomm.ftccommon.UpdateUI
import com.qualcomm.hardware.HardwareFactory
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister
import com.qualcomm.robotcore.robocol.Command
import org.firstinspires.ftc.robotcore.internal.network.CallbackResult
import org.firstinspires.ftc.robotcore.internal.network.PreferenceRemoterRC

class MyFtcEventLoop(hardwareFactory: HardwareFactory,
                     userOpmodeRegister: OpModeRegister,
                     callback: UpdateUI.Callback,
                     activityContext: Activity,
                     programmingModeController: ProgrammingModeController)
	: FtcEventLoop(hardwareFactory, userOpmodeRegister, callback, activityContext, programmingModeController) {
	override fun processCommand(command: Command): CallbackResult {
		var result = CallbackResult.HANDLED

		val name = command.name
		val extra = command.extra

		when (name) {
			CommandList.CMD_RESTART_ROBOT                      -> handleCommandRestartRobot()
			CommandList.CMD_REQUEST_CONFIGURATIONS             -> handleCommandRequestConfigurations()
			CommandList.CMD_REQUEST_REMEMBERED_GROUPS          -> handleCommandRequestRememberedGroups()
			CommandList.CMD_CLEAR_REMEMBERED_GROUPS            -> handleCommandClearRememberedGroups()
			CommandList.CMD_LYNX_FIRMWARE_UPDATE               -> handleCommandLynxFirmwareUpdate(command)
			CommandList.CMD_GET_USB_ACCESSIBLE_LYNX_MODULES    -> handleCommandGetUSBAccessibleLynxModules(command)
			CommandList.CMD_LYNX_ADDRESS_CHANGE                -> handleCommandLynxChangeModuleAddresses(command)
			CommandList.CMD_GET_CANDIDATE_LYNX_FIRMWARE_IMAGES -> handleCommandGetCandidateLynxFirmwareImages(command)
			CommandList.CMD_REQUEST_INSPECTION_REPORT          -> handleCommandRequestInspectionReport()
			CommandList.CMD_REQUEST_ABOUT_INFO                 -> handleCommandRequestAboutInfo(command)
			CommandList.CMD_DISCONNECT_FROM_WIFI_DIRECT        -> handleCommandDisconnectWifiDirect()
			CommandList.CMD_REQUEST_CONFIGURATION_TEMPLATES    -> handleCommandRequestConfigurationTemplates()
			CommandList.CMD_REQUEST_PARTICULAR_CONFIGURATION   -> handleCommandRequestParticularConfiguration(extra)
			CommandList.CMD_ACTIVATE_CONFIGURATION             -> handleCommandActivateConfiguration(extra)
			CommandList.CMD_REQUEST_UI_STATE                   -> sendUIState()
			CommandList.CMD_SAVE_CONFIGURATION                 -> handleCommandSaveConfiguration(extra)
			CommandList.CMD_DELETE_CONFIGURATION               -> handleCommandDeleteConfiguration(extra)
			CommandList.CMD_START_PROGRAMMING_MODE             -> handleCommandStartProgrammingMode()
			CommandList.CMD_START_DS_PROGRAM_AND_MANAGE        -> handleCommandStartDriverStationProgramAndManage()
			CommandList.CMD_STOP_PROGRAMMING_MODE              -> handleCommandStopProgrammingMode()
			CommandList.CMD_SHOW_TOAST                         -> handleCommandShowToast(command)
			CommandList.CMD_SHOW_DIALOG                        -> handleCommandShowDialog(command)
			CommandList.CMD_DISMISS_DIALOG                     -> handleCommandDismissDialog(command)
			CommandList.CMD_DISMISS_ALL_DIALOGS                -> handleCommandDismissAllDialogs(command)
			CommandList.CMD_SHOW_PROGRESS                      -> handleCommandShowProgress(command)
			CommandList.CMD_DISMISS_PROGRESS                   -> handleCommandDismissProgress(command)
			CommandList.CMD_ROBOT_CONTROLLER_PREFERENCE        -> result = PreferenceRemoterRC.getInstance().handleCommandRobotControllerPreference(extra)
			CommandList.CmdPlaySound.Command                   -> {/*result = SoundPlayer.getInstance().handleCommandPlaySound(extra)*/
			}
			CommandList.CmdRequestSound.Command                -> {/*result = SoundPlayer.getInstance().handleCommandRequestSound(command)*/
			}
			CommandList.CmdStopPlayingSounds.Command           -> {/*result = SoundPlayer.getInstance().handleCommandStopPlayingSounds(command)*/
			}
			CommandList.CmdVisuallyIdentify.Command            -> result = handleCommandVisuallyIdentify(command)
			else                                               -> result = CallbackResult.NOT_HANDLED
		}
		return result
	}
}
