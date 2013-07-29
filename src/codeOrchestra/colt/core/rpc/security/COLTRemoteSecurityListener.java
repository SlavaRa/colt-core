package codeOrchestra.colt.core.rpc.security;

/**
 * @author Alexander Eliseyev
 */
public interface COLTRemoteSecurityListener {

  void onNewRequest(String requestor, String shortCode);
  
  void onSuccessfulActivation(String shortCode);
  
}
