/* ************************************************************************** */
/*                                                                            */
/*                                                                            */
/*   HeaderGenerator.java                                                     */
/*                                                                            */
/*   By: Loïc <lbertran@student.42lyon.fr>                                    */
/*                                                                            */
/*   Created: 2020/11/04 15:57:00 by Loïc                                     */
/*   Updated: 2020/11/06 14:23:40 by Loïc                                     */
/*                                                                            */
/* ************************************************************************** */
package me.loic;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;

public class HeaderGenerator extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        VirtualFile virtualFile = event.getData(VIRTUAL_FILE);
        String fileName, user, user2, user3;
        for (fileName = virtualFile.getName(); fileName.length() < 51; fileName += ' '){}
        for (user = "By: " + System.getenv("USER") + " <" + System.getenv("MAIL") + ">"; user.length() < 47; user += ' '){}
        for (user2 = "by " + System.getenv("USER"); user2.length() < 20; user2 += ' '){}
        for (user3 = "by " + System.getenv("USER"); user3.length() < 20; user3 += ' '){}
        String startComment = "/*";
        String endComment = "*/";
        Date date = new Date();
        String header = startComment + " " + "************************************************************************** " + endComment + "\n" +
                startComment + "                                                                            " + endComment + "\n" +
                startComment + "                                                                            " + endComment + "\n" +
                startComment + "   " + fileName + "                      " + endComment + "\n" +
                startComment + "                                                                            " + endComment + "\n" +
                startComment + "   " + user + "                          " + endComment + "\n" +
                startComment + "                                                                            " + endComment + "\n" +
                startComment + "   Created: " + dateFormat.format(date) + " " + user2 + "                        " + endComment + "\n" +
                startComment + "   Updated: " + dateFormat.format(date) + " " + user3 + "                        " + endComment + "\n" +
                startComment + "                                                                            " + endComment + "\n" +
                startComment + " " + "************************************************************************** " + endComment + "\n";

        Runnable runnable = () -> event.getData(LangDataKeys.EDITOR).getDocument().insertString(0, header);
        WriteCommandAction.runWriteCommandAction(getEventProject(event), runnable);
    }
}