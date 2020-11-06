/* ************************************************************************** */
/*                                                                            */
/*                                                                            */
/*   HeaderUpdater.java                                                       */
/*                                                                            */
/*   By: Loïc <lbertran@student.42lyon.fr>                                    */
/*                                                                            */
/*   Created: 2020/11/04 15:58:08 by Loïc                                     */
/*   Updated: 2020/11/06 13:35:54 by Loïc                                     */
/*                                                                            */
/* ************************************************************************** */
package me.loic;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;

public class HeaderUpdater extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        VirtualFile virtualFile = event.getData(VIRTUAL_FILE);
        String fileName, user;
        String start = FileDocumentManager.getInstance().getDocument(virtualFile).getText(new TextRange(0, 5));
        if (!start.equals("/* **"))
            return;
        for (fileName = virtualFile.getName(); fileName.length() < 51; fileName += ' ') {
        }
        for (user = "by " + System.getenv("USER"); user.length() < 20; user += ' ') {
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String header = "/*   Updated: " + dateFormat.format(date) + " " + user + "                        */\n";
        Runnable runnable = () -> FileDocumentManager.getInstance().getDocument(virtualFile).replaceString(648, 648 + header.length(), header);
        WriteCommandAction.runWriteCommandAction(getEventProject(event), runnable);
    }
}