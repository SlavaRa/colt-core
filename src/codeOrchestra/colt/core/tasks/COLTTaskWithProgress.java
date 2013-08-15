package codeOrchestra.colt.core.tasks;

import codeOrchestra.colt.core.loading.LiveCodingHandlerManager;
import codeOrchestra.colt.core.ui.components.COLTProgressIndicator;

/**
 * @author Alexander Eliseyev
 */
public abstract class COLTTaskWithProgress<R> extends COLTTask<R> {

    @Override
    protected final R call() throws Exception {
        return call(LiveCodingHandlerManager.getInstance().getCurrentHandler().getProgressIndicator());
    }

    protected abstract R call(COLTProgressIndicator progressIndicator);

}