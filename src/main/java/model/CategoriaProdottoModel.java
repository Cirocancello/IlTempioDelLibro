package model;

import java.sql.SQLException;
import java.util.Collection;
import model.bean.CategoriaProdottoBean;
import model.interf.IModel;

public class CategoriaProdottoModel implements IModel<CategoriaProdottoBean> {

	@Override
	public void doSave(CategoriaProdottoBean bean) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean doDelete(int objectId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CategoriaProdottoBean doRetrieveByKey(int objectId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CategoriaProdottoBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
