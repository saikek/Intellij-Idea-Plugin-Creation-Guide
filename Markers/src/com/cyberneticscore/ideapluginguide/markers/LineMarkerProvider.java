package com.cyberneticscore.ideapluginguide.markers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;

public class LineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            Collection<? super RelatedItemLineMarkerInfo> result) {

        if (!(element instanceof PsiVariable)) {
            return;
        }
        if ((element instanceof PsiParameter)) {
            return;
        } //i'm skipping method parameters here

        PsiVariable field = (PsiVariable) element;
        PsiType type = field.getType();

        if (!(type instanceof PsiClassReferenceType)) {
            return;
        }

        Icon icon = null;

        final String className = type.getCanonicalText();

        if (className.contains("List")) {
            icon = Icons.Plus;
        }

        if (className.contains("Map")) {
            icon = Icons.Lock;
        }

        if (className.startsWith("String")) {
            icon = Icons.Pin;
        }

        if (icon == null) {
            return;
        }

        NavigationGutterIconBuilder<PsiElement> builder = NavigationGutterIconBuilder
                .create(icon)
                .setTargets(element);
        result.add(builder.createLineMarkerInfo(element));
    }
}
