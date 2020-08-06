package com.arconsis.measure

import android.content.res.Resources
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.PluginRegistry.Registrar

/** MeasurePlugin */
class MeasurePlugin : FlutterPlugin, MethodCallHandler {
	override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
		val channel = MethodChannel(flutterPluginBinding.binaryMessenger, "measure")
		channel.setMethodCallHandler(MeasurePlugin())
	}

	// This static function is optional and equivalent to onAttachedToEngine. It supports the old
	// pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
	// plugin registration via this function while apps migrate to use the new Android APIs
	// post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
	//
	// It is encouraged to share logic between onAttachedToEngine and registerWith to keep
	// them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
	// depending on the user's project. onAttachedToEngine or registerWith must both be defined
	// in the same class.
	companion object {
		@JvmStatic
		fun registerWith(registrar: Registrar) {
			val channel = MethodChannel(registrar.messenger(), "measure")
			channel.setMethodCallHandler(MeasurePlugin())
		}
	}

	override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
	}

	override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
		if (call.method == "getPhysicalPixelsPerInch") {
			result.success(Resources.getSystem().displayMetrics.xdpi)
		} else {
			result.notImplemented()
		}
	}
}