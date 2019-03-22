package stone_editor;

import java.util.Arrays;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class 内容辅助处理器 implements IContentAssistProcessor {

  public static final String[] 所有建议 = new String[] {"新建"};

  @Override
  public ICompletionProposal[] computeCompletionProposals(ITextViewer 视图, int 偏移) {

    IDocument 文件 = 视图.getDocument();

    try {
      int 偏移所在行 = 文件.getLineOfOffset(偏移);
      int 行头偏移 = 文件.getLineOffset(偏移所在行);

      int 当前行文本长度 = 偏移 - 行头偏移;
      String 当前行文本 = 文件.get(行头偏移, 当前行文本长度).toLowerCase();

      return Arrays.asList(所有建议).stream()
          .filter(建议 -> !视图.getDocument().get().contains(建议) && 建议.toLowerCase().startsWith(当前行文本))
          .map(建议 -> new CompletionProposal(建议, 行头偏移, 当前行文本长度, 建议.length()))
          .toArray(ICompletionProposal[]::new);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
    return new ICompletionProposal[0];
  }

  @Override
  public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
    return null;
  }

  @Override
  public char[] getCompletionProposalAutoActivationCharacters() {
    String keys = "新";
    return keys.toCharArray();
  }

  @Override
  public char[] getContextInformationAutoActivationCharacters() {
    String keys = "";
    return keys.toCharArray();
  }

  @Override
  public String getErrorMessage() {
    return null;
  }

  @Override
  public IContextInformationValidator getContextInformationValidator() {
    return null;
  }

}
