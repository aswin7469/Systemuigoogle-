package com.google.android.material.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.SoftwareKeyboardControllerCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.DialogFragment;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class MaterialDatePicker extends DialogFragment {
    public MaterialShapeDrawable background;
    public MaterialCalendar calendar;
    public CalendarConstraints calendarConstraints;
    public Button confirmButton;
    public boolean edgeToEdgeEnabled;
    public boolean fullscreen;
    public CheckableImageButton headerToggleButton;
    public int inputMode;
    public CharSequence negativeButtonText;
    public int negativeButtonTextResId;
    public final LinkedHashSet onCancelListeners = new LinkedHashSet();
    public final LinkedHashSet onDismissListeners = new LinkedHashSet();
    public int overrideThemeResId;
    public PickerFragment pickerFragment;
    public CharSequence positiveButtonText;
    public int positiveButtonTextResId;
    public CharSequence titleText;
    public int titleTextResId;

    public MaterialDatePicker() {
        new LinkedHashSet();
        new LinkedHashSet();
    }

    public static int getPaddedPickerWidth(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(2131166809);
        int i = new Month(UtcDates.getTodayCalendar()).daysInWeek;
        int dimensionPixelSize = resources.getDimensionPixelSize(2131166815) * i;
        return ((i - 1) * resources.getDimensionPixelOffset(2131166829)) + dimensionPixelSize + (dimensionPixelOffset * 2);
    }

    public static boolean readMaterialCalendarStyleBoolean(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.resolveTypedValueOrThrow(context, MaterialCalendar.class.getCanonicalName(), 2130969537).data, new int[]{i});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    public final void getDateSelector() {
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mArguments.getParcelable("DATE_SELECTOR_KEY"));
    }

    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.onCancelListeners.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = this.mArguments;
        }
        this.overrideThemeResId = bundle.getInt("OVERRIDE_THEME_RES_ID");
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(bundle.getParcelable("DATE_SELECTOR_KEY"));
        this.calendarConstraints = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.titleTextResId = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.titleText = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.inputMode = bundle.getInt("INPUT_MODE_KEY");
        this.positiveButtonTextResId = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.positiveButtonText = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.negativeButtonTextResId = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.negativeButtonText = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
    }

    public final Dialog onCreateDialog() {
        Context requireContext = requireContext();
        requireContext();
        int i = this.overrideThemeResId;
        if (i != 0) {
            Dialog dialog = new Dialog(requireContext, i);
            Context context = dialog.getContext();
            this.fullscreen = readMaterialCalendarStyleBoolean(16843277, context);
            int i2 = MaterialAttributes.resolveTypedValueOrThrow(context, MaterialDatePicker.class.getCanonicalName(), 2130968887).data;
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context, (AttributeSet) null, 2130969537, 2132018823);
            this.background = materialShapeDrawable;
            materialShapeDrawable.initializeElevationOverlay(context);
            this.background.setFillColor(ColorStateList.valueOf(i2));
            MaterialShapeDrawable materialShapeDrawable2 = this.background;
            View decorView = dialog.getWindow().getDecorView();
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            materialShapeDrawable2.setElevation(ViewCompat.Api21Impl.getElevation(decorView));
            return dialog;
        }
        getDateSelector();
        throw null;
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        String str;
        if (this.fullscreen) {
            i = 2131558822;
        } else {
            i = 2131558821;
        }
        View inflate = layoutInflater.inflate(i, viewGroup);
        Context context = inflate.getContext();
        if (this.fullscreen) {
            inflate.findViewById(2131363147).setLayoutParams(new LinearLayout.LayoutParams(getPaddedPickerWidth(context), -2));
        } else {
            inflate.findViewById(2131363148).setLayoutParams(new LinearLayout.LayoutParams(getPaddedPickerWidth(context), -1));
        }
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        boolean z = true;
        ((TextView) inflate.findViewById(2131363159)).setAccessibilityLiveRegion(1);
        this.headerToggleButton = (CheckableImageButton) inflate.findViewById(2131363161);
        TextView textView = (TextView) inflate.findViewById(2131363165);
        CharSequence charSequence = this.titleText;
        if (charSequence != null) {
            textView.setText(charSequence);
        } else {
            textView.setText(this.titleTextResId);
        }
        this.headerToggleButton.setTag("TOGGLE_BUTTON_TAG");
        CheckableImageButton checkableImageButton = this.headerToggleButton;
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842912}, AppCompatResources.getDrawable(2131233272, context));
        stateListDrawable.addState(new int[0], AppCompatResources.getDrawable(2131233274, context));
        checkableImageButton.setImageDrawable(stateListDrawable);
        CheckableImageButton checkableImageButton2 = this.headerToggleButton;
        if (this.inputMode == 0) {
            z = false;
        }
        checkableImageButton2.setChecked(z);
        ViewCompat.setAccessibilityDelegate(this.headerToggleButton, (AccessibilityDelegateCompat) null);
        CheckableImageButton checkableImageButton3 = this.headerToggleButton;
        if (checkableImageButton3.checked) {
            str = checkableImageButton3.getContext().getString(2131953321);
        } else {
            str = checkableImageButton3.getContext().getString(2131953323);
        }
        this.headerToggleButton.setContentDescription(str);
        this.headerToggleButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MaterialDatePicker materialDatePicker = MaterialDatePicker.this;
                Button button = materialDatePicker.confirmButton;
                materialDatePicker.getDateSelector();
                throw null;
            }
        });
        this.confirmButton = (Button) inflate.findViewById(2131362297);
        getDateSelector();
        throw null;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.onDismissListeners.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) this.mView;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, com.google.android.material.datepicker.CalendarConstraints$Builder] */
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.overrideThemeResId);
        Month month = null;
        bundle.putParcelable("DATE_SELECTOR_KEY", (Parcelable) null);
        CalendarConstraints calendarConstraints2 = this.calendarConstraints;
        ? obj = new Object();
        int i = CalendarConstraints.Builder.$r8$clinit;
        int i2 = CalendarConstraints.Builder.$r8$clinit;
        long j = calendarConstraints2.start.timeInMillis;
        long j2 = calendarConstraints2.end.timeInMillis;
        obj.openAt = Long.valueOf(calendarConstraints2.openAt.timeInMillis);
        int i3 = calendarConstraints2.firstDayOfWeek;
        CalendarConstraints.DateValidator dateValidator = calendarConstraints2.validator;
        Month month2 = this.calendar.current;
        if (month2 != null) {
            obj.openAt = Long.valueOf(month2.timeInMillis);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", dateValidator);
        Month create = Month.create(j);
        Month create2 = Month.create(j2);
        CalendarConstraints.DateValidator dateValidator2 = (CalendarConstraints.DateValidator) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY");
        Long l = obj.openAt;
        if (l != null) {
            month = Month.create(l.longValue());
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new CalendarConstraints(create, create2, dateValidator2, month, i3));
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.titleTextResId);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.titleText);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.positiveButtonTextResId);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.positiveButtonText);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.negativeButtonTextResId);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.negativeButtonText);
    }

    public final void onStart() {
        Integer num;
        boolean z;
        int i;
        boolean z2;
        boolean z3;
        super.onStart();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (this.fullscreen) {
                window.setLayout(-1, -1);
                window.setBackgroundDrawable(this.background);
                if (!this.edgeToEdgeEnabled) {
                    final View findViewById = requireView().findViewById(2131362616);
                    if (findViewById.getBackground() instanceof ColorDrawable) {
                        num = Integer.valueOf(((ColorDrawable) findViewById.getBackground()).getColor());
                    } else {
                        num = null;
                    }
                    if (num == null || num.intValue() == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Context context = window.getContext();
                    TypedValue resolve = MaterialAttributes.resolve(16842801, context);
                    if (resolve != null) {
                        int i2 = resolve.resourceId;
                        if (i2 != 0) {
                            i = context.getColor(i2);
                        } else {
                            i = resolve.data;
                        }
                    } else {
                        i = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
                    }
                    if (z) {
                        num = Integer.valueOf(i);
                    }
                    window.setDecorFitsSystemWindows(false);
                    window.getContext();
                    window.getContext();
                    window.setStatusBarColor(0);
                    window.setNavigationBarColor(0);
                    boolean isColorLight = MaterialColors.isColorLight(num.intValue());
                    if (MaterialColors.isColorLight(0) || isColorLight) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    new SoftwareKeyboardControllerCompat.Impl30(window.getDecorView());
                    WindowInsetsControllerCompat.Impl30 impl30 = new WindowInsetsControllerCompat.Impl30(window);
                    Window window2 = impl30.mWindow;
                    WindowInsetsController windowInsetsController = impl30.mInsetsController;
                    if (z2) {
                        if (window2 != null) {
                            View decorView = window2.getDecorView();
                            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
                        }
                        windowInsetsController.setSystemBarsAppearance(8, 8);
                    } else {
                        if (window2 != null) {
                            View decorView2 = window2.getDecorView();
                            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & -8193);
                        }
                        windowInsetsController.setSystemBarsAppearance(0, 8);
                    }
                    boolean isColorLight2 = MaterialColors.isColorLight(i);
                    if (MaterialColors.isColorLight(0) || isColorLight2) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    new SoftwareKeyboardControllerCompat.Impl30(window.getDecorView());
                    WindowInsetsControllerCompat.Impl30 impl302 = new WindowInsetsControllerCompat.Impl30(window);
                    Window window3 = impl302.mWindow;
                    WindowInsetsController windowInsetsController2 = impl302.mInsetsController;
                    if (z3) {
                        if (window3 != null) {
                            View decorView3 = window3.getDecorView();
                            decorView3.setSystemUiVisibility(decorView3.getSystemUiVisibility() | 16);
                        }
                        windowInsetsController2.setSystemBarsAppearance(16, 16);
                    } else {
                        if (window3 != null) {
                            View decorView4 = window3.getDecorView();
                            decorView4.setSystemUiVisibility(decorView4.getSystemUiVisibility() & -17);
                        }
                        windowInsetsController2.setSystemBarsAppearance(0, 16);
                    }
                    final int paddingTop = findViewById.getPaddingTop();
                    final int i3 = findViewById.getLayoutParams().height;
                    AnonymousClass3 r5 = new OnApplyWindowInsetsListener() {
                        public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                            int i = windowInsetsCompat.mImpl.getInsets(7).top;
                            int i2 = i3;
                            View view2 = findViewById;
                            if (i2 >= 0) {
                                view2.getLayoutParams().height = i2 + i;
                                view2.setLayoutParams(view2.getLayoutParams());
                            }
                            view2.setPadding(view2.getPaddingLeft(), paddingTop + i, view2.getPaddingRight(), view2.getPaddingBottom());
                            return windowInsetsCompat;
                        }
                    };
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(findViewById, r5);
                    this.edgeToEdgeEnabled = true;
                }
            } else {
                window.setLayout(-2, -2);
                int dimensionPixelOffset = requireContext().getResources().getDimensionPixelOffset(2131166817);
                Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
                window.setBackgroundDrawable(new InsetDrawable(this.background, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
                View decorView5 = window.getDecorView();
                Dialog dialog2 = this.mDialog;
                if (dialog2 != null) {
                    decorView5.setOnTouchListener(new InsetDialogOnTouchListener(dialog2, rect));
                } else {
                    throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
                }
            }
            requireContext();
            int i4 = this.overrideThemeResId;
            if (i4 != 0) {
                getDateSelector();
                CalendarConstraints calendarConstraints2 = this.calendarConstraints;
                MaterialCalendar materialCalendar = new MaterialCalendar();
                Bundle bundle = new Bundle();
                bundle.putInt("THEME_RES_ID_KEY", i4);
                bundle.putParcelable("GRID_SELECTOR_KEY", (Parcelable) null);
                bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints2);
                bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints2.openAt);
                materialCalendar.setArguments(bundle);
                this.calendar = materialCalendar;
                PickerFragment pickerFragment2 = materialCalendar;
                if (this.headerToggleButton.checked) {
                    getDateSelector();
                    CalendarConstraints calendarConstraints3 = this.calendarConstraints;
                    PickerFragment materialTextInputPicker = new MaterialTextInputPicker();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("THEME_RES_ID_KEY", i4);
                    bundle2.putParcelable("DATE_SELECTOR_KEY", (Parcelable) null);
                    bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints3);
                    materialTextInputPicker.setArguments(bundle2);
                    pickerFragment2 = materialTextInputPicker;
                }
                this.pickerFragment = pickerFragment2;
                getDateSelector();
                getContext();
                throw null;
            }
            getDateSelector();
            throw null;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public final void onStop() {
        this.pickerFragment.onSelectionChangedListeners.clear();
        super.onStop();
    }
}
