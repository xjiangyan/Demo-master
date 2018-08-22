package com.example.hp.demo.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class EnterAnimation extends Visibility {
    private static final String BOTTOMVIEW = "bottomview";
    private final View animView;
    private final Context context;

    public EnterAnimation(View animView, Context context) {
        this.animView = animView;
        this.context = context;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        int transY = (int) (context.getResources().getDisplayMetrics().density * 56 * 2);
        transitionValues.values.put(BOTTOMVIEW, transY);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        //        animView.measure(0, 0);
        //        int measuredHeight = animView.getMeasuredHeight();
        transitionValues.values.put(BOTTOMVIEW, 0);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        return super.createAnimator(sceneRoot, startValues, endValues);
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, final View view, TransitionValues startValues, TransitionValues endValues) {

        if (startValues == null || endValues == null) {
            return null;
        }
        int startposition = (int) startValues.values.get(BOTTOMVIEW);
        int endposition = (int) endValues.values.get(BOTTOMVIEW);
        if (startposition != endposition && view == animView) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(startposition, endposition);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Object transY = animation.getAnimatedValue();
                    if (transY != null) {
                        view.setTranslationY((Integer) transY);
                    }
                }
            });
            return valueAnimator;
        }
        return null;
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, final View view, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }
        int endposition = (int) startValues.values.get(BOTTOMVIEW);
        int startposition = (int) endValues.values.get(BOTTOMVIEW);
        if (startposition != endposition && view == animView) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(startposition, endposition);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Object transY = animation.getAnimatedValue();
                    if (transY != null) {
                        view.setTranslationY((Integer) transY);
                    }
                }
            });
            return valueAnimator;
        }
        return null;
    }
}
