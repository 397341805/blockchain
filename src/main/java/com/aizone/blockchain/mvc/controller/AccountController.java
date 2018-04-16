package com.aizone.blockchain.mvc.controller;

import com.aizone.blockchain.db.DBUtils;
import com.aizone.blockchain.utils.JsonVo;
import com.aizone.blockchain.wallet.Account;
import com.aizone.blockchain.wallet.Personal;
import com.google.common.base.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yangjian
 * @since 18-4-8
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	/**
	 * 创建账户
	 * @param request
	 * @return
	 */
	@PostMapping("/new")
	public JsonVo newAccount(HttpServletRequest request) throws Exception {

		String password = request.getParameter("password");
		Account account = Personal.newAccount();
		return new JsonVo(JsonVo.CODE_SUCCESS, "New account created, please remember your Address and Private Key.",
				account);
	}

	/**
	 * 获取挖矿账号
	 * @param request
	 * @return
	 */
	@GetMapping("/coinbase")
	public JsonVo coinbase(HttpServletRequest request) {

		Optional<Account> coinBaseAccount = DBUtils.getCoinBaseAccount();
		JsonVo success = JsonVo.success();
		if (coinBaseAccount.isPresent()) {
			success.setItem(coinBaseAccount.get());
		} else {
			success.setMessage("CoinBase Account is not created");
		}
		return success;
	}

	/**
	 * 列出所有的账号
	 * @param request
	 * @return
	 */
	@GetMapping("/list")
	public JsonVo listAccounts(HttpServletRequest request) {

		List<Account> accounts = DBUtils.listAccounts();
		JsonVo success = JsonVo.success();
		success.setItem(accounts);
		return success;
	}
}
